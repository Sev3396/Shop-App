package com.example.shop_app.nav

sealed class Screen(val route: String) {
    object ProductSearch : Screen("product_search")
    object ProductDetails : Screen("product_details")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
