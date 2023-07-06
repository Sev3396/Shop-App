package com.example.shop_app.ui.view.screen.product_search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.shop_app.R
import com.example.shop_app.components.Utils
import com.example.shop_app.nav.Screen
import com.example.shop_app.ui.theme.Dimensions
import com.example.shop_app.ui.view.global_components.HorizontalSpacer
import com.example.shop_app.ui.view.global_components.RecentlyViewedProducts
import com.example.shop_app.ui.view.screen.product_search.MainViewModel.Event

/**
 * Display the Product Description of the selected product
 * @param navController - NavController for compose screen Navigation
 * @param viewModel
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductSearchView(
    navController: NavController,
    viewModel: MainViewModel
) {
    var searchTextState by rememberSaveable {
        mutableStateOf("")
    }
    var searchErrorState by rememberSaveable {
        mutableStateOf("")
    }
    val productState = viewModel.state.value
    val recentlyViewedProductsState = viewModel.recentlyViewedProductsState.value
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        // Page Loader
        if (productState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(Dimensions.normal_50),
                strokeWidth = Dimensions.normal_5
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalSpacer(Dimensions.normal_15)

            Column {

                // Search query input field
                OutlinedTextField(
                    value = searchTextState,
                    label = {
                        Text(text = Utils.search_input)
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = androidx.compose.ui.text.input.ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (searchTextState.isNotEmpty()) {
                                keyboardController?.hide()
                                viewModel.onEvent(
                                    Event.SearchProduct(searchTextState)
                                )
                            }
                        }
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimensions.normal_10)
                        .testTag(stringResource(id = R.string.search_text)),
                    onValueChange = {
                        searchTextState = it
                        searchErrorState = if (Utils.isValidInput(it).first.not())
                            Utils.isValidInput(it).second
                        else ""
                    },
                    isError = searchErrorState.isNotEmpty(),
                )

                // Search query error text
                if (searchErrorState.isNotEmpty()) {
                    Text(
                        text = searchErrorState,
                        modifier = Modifier
                            .padding(Dimensions.normal_5)
                            .testTag(stringResource(id = R.string.error_textField)),
                        color = Color.Red
                    )
                }

                HorizontalSpacer(Dimensions.normal_10)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimensions.normal_10)
                        .padding(top = Dimensions.normal_5),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    // Search button
                    Button(
                        onClick = {
                            if (searchTextState.isNotEmpty()) {
                                viewModel.onEvent(
                                    Event.SearchProduct(searchTextState)
                                )
                                keyboardController?.hide()
                            } else {
                                searchErrorState = Utils.empty_search
                            }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.button_search))
                    }

                    // Clear Button
                    Button(
                        onClick = {
                            searchTextState = ""
                            searchErrorState = ""
                            viewModel.onEvent(Event.ClearUI)
                        }
                    ) {
                        Text(text = stringResource(id = R.string.button_clear))
                    }
                }
            }

            ProductList(
                productResponse = productState
            ) {
                navController.navigate(Screen.ProductDetails.withArgs(it))
            }
        }

        // Recently Viewed Products
        if (recentlyViewedProductsState.recentlyViewedProducts.isNotEmpty() &&
            productState.productList.products.isEmpty()
        ) {
            RecentlyViewedProducts(
                modifier = Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(Dimensions.normal_280),
                productList = recentlyViewedProductsState.recentlyViewedProducts,
                onClearClick = {
                    viewModel.onEvent(Event.ClearRVP)
                }
            ) {
                navController.navigate(Screen.ProductDetails.withArgs(it))
            }
        }
    }
}
