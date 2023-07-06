package com.example.shop_app.components

import org.junit.Assert.*
import org.junit.Test

class UtilsTest {

    @Test
    fun validateInvalidSearch() {
        val searchQuery = "b@g"
        assertEquals(false, Utils.isValidInput(searchQuery).first)
    }

    @Test
    fun validateValidSearch() {
        val searchQuery = "bag"
        assertEquals(true, Utils.isValidInput(searchQuery).first)
    }
}
