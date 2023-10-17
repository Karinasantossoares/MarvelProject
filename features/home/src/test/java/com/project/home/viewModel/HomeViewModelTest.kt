package com.project.home.viewModel

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.project.home.MainDispatcherRule
import com.project.home.domain.usecase.HomeUseCase
import com.project.home.presentation.HomeAction
import com.project.home.presentation.HomeState
import com.project.home.presentation.HomeViewModel
import com.project.home.stub.getModelStub
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = MainDispatcherRule()

    val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val useCase: HomeUseCase = mockk(relaxed = true)
    private val application: Application = mockk(relaxed = true)

    @Test
    fun `when call refresh with success return list of characters`() = runTest {
        val currentPage = "0"
        val expectedList = getModelStub()

        // Given
        coEvery { useCase.invoke(currentPage, null) } returns flowOf(expectedList)

        // When
        val viewModel = HomeViewModel(
            useCase = useCase,
            application = application,
        )

        viewModel.stateLiveData.test {
            assertEquals(
                HomeState(
                    listCharacter = expectedList.toMutableList(),
                ), awaitItem()
            )
        }
    }

    @Test
    fun `when call refresh with error return list of character`() = runTest {
        val currentPage = "0"
        // Given
        val errorExpected = Exception()
        val errorMessage = "Erro ao processar sua solicitação. Por favor , novamente mais tarde"
        val drawable = mockk<Drawable>(relaxed = true)
        coEvery { useCase.invoke(currentPage, null) } returns flow { throw errorExpected }
        coEvery { application.getString(com.project.ui.R.string.message_error_generic) } returns errorMessage
        mockkStatic(AppCompatResources::class)
        coEvery { AppCompatResources.getDrawable(
            application, com.project.ui.R.drawable.image_generic_error)
        } returns drawable


        // When
        val viewModel = HomeViewModel(
            useCase = useCase,
            application = application,
        )

        viewModel.eventLiveData.test {
            assertTrue(awaitItem() is HomeAction.ShowError)
        }
    }
}