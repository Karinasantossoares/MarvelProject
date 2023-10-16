package com.project.detail.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.project.detail.R
import com.project.detail.presentation.DetailFragment.Companion.KEY_ARGS_ID
import com.project.detail.presentation.DetailFragment.Companion.KEY_ARGS_PHOTO
import com.project.navigation.DetailNavigation

class DetailNavigationImpl : DetailNavigation {
    override fun navigateToDetail(navController: NavController, id: String, urlPhoto: String) {
        val bundle = bundleOf(KEY_ARGS_ID to id, KEY_ARGS_PHOTO to urlPhoto)
        navController.navigate(R.id.detail_nav_graph, bundle)
    }
}