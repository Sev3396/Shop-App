package com.example.shop_app.view

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.example.shop_app.MainActivity
import com.example.shop_app.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

private const val WAIT_UNTIL_TIMEOUT = 3_000L

class ProductListKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun testEmptyProductSearch() {
        val tag = context.getString(R.string.search_text)
        val searchButton = context.getString(R.string.button_search)
        val errorText = context.getString(R.string.error_textField)
        val sampleSearch = ""
        composeTestRule.onNodeWithTag(tag).performTextInput(sampleSearch)
        composeTestRule.onNodeWithText(searchButton).performClick()
        composeTestRule.onNodeWithTag(errorText).assertExists()
    }

    @Test
    fun testSuccessProductSearch() {
        val tag = context.getString(R.string.search_text)
        val searchButton = context.getString(R.string.button_search)
        val productList = context.getString(R.string.product_list)
        val sampleSearch = "Bag"
        composeTestRule.onNodeWithTag(tag).performTextInput(sampleSearch)
        composeTestRule.onNodeWithText(searchButton).performClick()
        composeTestRule.waitUntilExists(hasTestTag(productList))
        composeTestRule.onNodeWithTag(productList).assertExists()
    }

    @Test
    fun testErrorProductSearch() {
        val tag = context.getString(R.string.search_text)
        val searchButton = context.getString(R.string.button_search)
        val errorMessage = context.getString(R.string.error)
        val sampleSearch = "skdjfhdf"
        composeTestRule.onNodeWithTag(tag).performTextInput(sampleSearch)
        composeTestRule.onNodeWithText(searchButton).performClick()
        composeTestRule.waitUntilExists(hasTestTag(errorMessage))
        composeTestRule.onNodeWithTag(errorMessage).assertIsDisplayed()
    }

    @Test
    fun testInvalidSearchQuery() {
        val tag = context.getString(R.string.search_text)
        val searchError = context.getString(R.string.error_textField)
        val sampleSearch = "b@g"
        composeTestRule.onNodeWithTag(tag).performTextInput(sampleSearch)
        composeTestRule.waitUntilExists(hasTestTag(searchError))
        composeTestRule.onNodeWithTag(searchError).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    fun ComposeContentTestRule.waitUntilExists(
        matcher: SemanticsMatcher,
        timeoutMillis: Long = WAIT_UNTIL_TIMEOUT
    ) = waitUntilNodeCount(matcher, 1, timeoutMillis)
}
