package com.project.navigation

import android.content.Context
import androidx.navigation.NavController

interface DetailNavigation {
    fun navigateToDetail(navController: NavController, id: String, urlPhoto: String)
}