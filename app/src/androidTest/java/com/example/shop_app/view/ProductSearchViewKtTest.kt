package com.example.shop_app.view

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.example.shop_app.MainActivity
import com.example.shop_app.R
import org.junit.Rule
import org.junit.Test

class ProductSearchViewKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun testSearchTextField() {
        val tag = context.getString(R.string.search_text)
        val sampleSearch = "laptop"
        composeTestRule.onNodeWithTag(tag).performTextInput(sampleSearch)
        composeTestRule.onNodeWithTag(tag).assertTextContains(sampleSearch)
    }

    @Test
    fun testClearButton() {
        val tag = context.getString(R.string.search_text)
        val clearButton = context.getString(R.string.button_clear)
        val sampleSearch = "laptop"
        composeTestRule.onNodeWithTag(tag).performTextInput(sampleSearch)
        composeTestRule.onNodeWithText(clearButton).performClick()
        composeTestRule.onNodeWithTag(tag).assertTextContains("")
    }
}
