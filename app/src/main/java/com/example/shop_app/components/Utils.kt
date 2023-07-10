package com.example.shop_app.components

object Utils {

    const val search_input = "Search for product"
    const val BASE_URL = "https://dummyjson.com/"
    const val errorMessage = "Sorry!!! couldn't reach server"
    const val empty_search = "Search query can't be empty"
    const val EMPTY_STRING = ""
    const val DEFAULT_PRODUCT_ID = "1"
    const val db_name = "shop_app_db"
    const val ZERO_RESULTS = "No products found for the search!!!"
    private const val INVALID_SEARCH = "Can't contain special characters!!!"
    private const val searchRegex = "^[a-zA-Z]*$"

    // checks whether search query is valid
    fun isValidInput(input: String): Pair<Boolean, String> {
        return if (input.matches(searchRegex.toRegex()).not())
            Pair(false, INVALID_SEARCH)
        else
            Pair(true, "")
    }

    fun Int.toPrice() = "$ $this"
}
