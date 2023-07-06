package com.example.shop_app.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shop_app.ui.view.screen.product_details.ProductDetailsView
import com.example.shop_app.ui.view.screen.product_details.ProductDetailsViewModel
import com.example.shop_app.ui.view.screen.product_search.MainViewModel
import com.example.shop_app.ui.view.screen.product_search.ProductSearchView

// Declare & Manage the App Screen Navigation
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ProductSearch.route) {
        composable(
            route = Screen.ProductSearch.route
        ) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            ProductSearchView(
                navController = navController,
                viewModel = mainViewModel
            )
        }

        /** ProductDetails Screen
         * @param productId
         */
        composable(
            route = Screen.ProductDetails.route + "/{productId}",
            arguments = listOf(
                navArgument(name = "productId") {
                    type = NavType.StringType
                    defaultValue = ""
                }

            )
        ) {
            val productDetailsViewModel = hiltViewModel<ProductDetailsViewModel>()
            ProductDetailsView(
                viewModel = productDetailsViewModel
            )
        }
    }
}
