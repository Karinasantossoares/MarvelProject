package com.project.home.repository

import app.cash.turbine.test
import com.project.commons.errorutils.error.ConnectionException
import com.project.commons.errorutils.error.HttpException
import com.project.home.data.datasource.local.LocalDataSource
import com.project.home.data.datasource.remote.RemoteDataSource
import com.project.home.data.mapper.toListEntity
import com.project.home.data.mapper.toModel
import com.project.home.data.repository.RemoteRepositoryImpl
import com.project.home.stub.getEntityStub
import com.project.home.stub.getResponseStub
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException

class HomeRepositoryIntegrationTest {

    private val remoteDataSource: RemoteDataSource = mockk(relaxed = true)
    private val localDataSource: LocalDataSource = mockk(relaxed = true)
    private val homeRepository = RemoteRepositoryImpl(remoteDataSource, localDataSource)

    @Test
    fun `getCharacterHome should return character when usecase return success`() = runBlocking {
        val expected = getResponseStub()
        val expectedModel = expected.toModel().data.results
        val currentPage = "1"
        coEvery { remoteDataSource.getAllCharacters(currentPage, "") } returns expected

        val result = homeRepository.getAllCharacter(currentPage, "")

        result.test {
            assertEquals(expectedModel, awaitItem())
            awaitComplete()
        }

        verify {
            localDataSource.saveCharacter(expectedModel.toListEntity(currentPage.toInt()))
        }
    }

    @Test
    fun `getCharacterHome Should return an error When service returns error with cache`() =
        runBlocking {
            val expectedCache = getEntityStub()
            val expectedCacheModel = expectedCache.map { it.toModel() }
            // Given
            val currentPage = "1"
            coEvery { remoteDataSource.getAllCharacters(currentPage, "") } throws IOException()
            coEvery { localDataSource.getAllCharacters(currentPage.toInt()) } returns expectedCache

            // When
            val result = homeRepository.getAllCharacter(currentPage, "")

            result.test {
                assertEquals(expectedCacheModel, awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `getCharacterHome Should return an error When service returns DefaultError`() =
        runBlocking {
            // Given
            val currentPage = "1"
            coEvery { remoteDataSource.getAllCharacters(currentPage, "") } throws Exception()
            coEvery { localDataSource.getAllCharacters(currentPage.toInt()) } returns emptyList()

            // When
            val result = homeRepository.getAllCharacter(currentPage, "")

            result.test {
                assertEquals(HttpException, awaitError())
            }
        }

    @Test
    fun `getCharacterHome Should return an error When service returns IOException`() = runBlocking {
        // Given
        val currentPage = "1"
        coEvery { remoteDataSource.getAllCharacters(currentPage, "") } throws IOException()
        coEvery { localDataSource.getAllCharacters(currentPage.toInt()) } returns emptyList()

        // When
        val result = homeRepository.getAllCharacter(currentPage, "")

        result.test {
            assertEquals(ConnectionException, awaitError())
        }
    }
}