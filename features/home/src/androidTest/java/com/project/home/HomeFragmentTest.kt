package com.project.home

import android.app.Activity
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.project.home.presentation.HomeFragment
import com.project.home.robot.HomeActionRobot
import com.project.home.stub.HomeStub
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.test.KoinTest


internal class HomeFragmentTest: KoinTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val robot = HomeActionRobot(
        mockk(relaxed = true),
        mockk(relaxed = true)
    )

    @Test
    fun verifySuccessListAndPagination() {
        val nameCharacter1 = "Hulk"
        val nameCharacter2 = "Iron Man"

        launchFragmentInContainer<HomeFragment>(initialState = Lifecycle.State.INITIALIZED).onFragment{
            robot
                .loadModule()
                .mockSuccess(page = "1", response = HomeStub.getSuccessStub(nameCharacter1))
                .mockSuccess(page = "2", response = HomeStub.getSuccessStub(nameCharacter2))
                .verifyTitleToolbar()
                .verifyNameInScreen(nameCharacter1)
                .scrollToEnd()
                .verifyNameInScreen(nameCharacter2)
                .unloadModule()
        }

    }
}
