package com.project.ui

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

fun ComponentActivity.handleOnBackPressed(
isEnabled: Boolean = true,
onBackPressed: () -> Unit
) = onBackPressedDispatcher.addCallback(
object : OnBackPressedCallback(isEnabled) {
    override fun handleOnBackPressed() = onBackPressed()
}
)

fun Fragment.onBackPressed(
    executeOnBack: (() -> Unit) = {
        activity?.supportFragmentManager?.popBackStackImmediate()
    }
) {
    activity?.onBackPressedDispatcher?.addCallback(
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                executeOnBack.invoke()
            }
        }
    )
}