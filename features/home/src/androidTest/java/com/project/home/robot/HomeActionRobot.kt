package com.project.home.robot

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.project.home.R
import com.project.home.data.api.HomeService
import com.project.home.data.model.CharacterResponse
import com.project.home.di.HomeModule
import com.project.home.stub.HomeStub
import com.project.persistence.dao.CharacterDao
import io.mockk.coEvery
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

internal class HomeActionRobot(
    private val picPayService: HomeService,
    private val characterDao: CharacterDao
) {
    val module by lazy {
        listOf(
            module {
                single { characterDao }
                single { picPayService }
            },
            HomeModule.homeModules()
        )
    }


    fun loadModule() = apply {
       loadKoinModules(module)
    }

    fun unloadModule() = apply {
       unloadKoinModules(module)
    }

    fun mockSuccess(page: String = "0", query: String = "", response: CharacterResponse) =
        apply {
            coEvery { picPayService.getAllCharacter(page, query) } returns response
        }

    fun mockError(page: String = "0", query: String = "", exception: Exception) = apply {
        coEvery { picPayService.getAllCharacter(page, query) } throws exception
    }

    fun verifyTitleToolbar() = apply {
        onView(ViewMatchers.withText("Personagens"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun verifyNameInScreen(name: String) = apply {
        onView(ViewMatchers.withText(name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun scrollToEnd() = apply {
        onView(withId(R.id.recyclerCharacter)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                HomeStub.getSizeSuccessStub() - 1
            )
        );
    }
}