package com.example.shop_app.ui.view.screen.product_search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.shop_app.R
import com.example.shop_app.ui.theme.Dimensions
import com.example.shop_app.ui.view.global_components.HorizontalSpacer
import com.example.shop_app.ui.view.global_components.ProductItemCard

@Composable
fun ProductList(
    productResponse: ProductListState,
    onItemClick: (productId: String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (productResponse.error.isNotEmpty()) {
            Text(
                text = productResponse.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(Dimensions.normal_10)
                    .testTag(stringResource(id = R.string.error)),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HorizontalSpacer(width = Dimensions.normal_15)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimensions.normal_10)
                    .testTag(stringResource(id = R.string.product_list)),
                horizontalArrangement = Arrangement.spacedBy(Dimensions.normal_5),
            ) {
                items(productResponse.productList.products) { product ->
                    ProductItemCard(
                        product = product,
                        onItemClick = onItemClick,
                        cardWidth = Dimensions.normal_300
                    )
                }
            }
        }
    }
}
