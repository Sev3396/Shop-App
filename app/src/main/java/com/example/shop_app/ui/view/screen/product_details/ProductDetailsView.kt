package com.example.shop_app.ui.view.screen.product_details

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shop_app.R
import com.example.shop_app.components.Utils.toPrice
import com.example.shop_app.ui.theme.Dimensions
import com.example.shop_app.ui.view.global_components.HorizontalSpacer

/**
 * Display the Product Description of the selected product
 * @param viewModel
 */

@Composable
fun ProductDetailsView(
    viewModel: ProductDetailsViewModel
) {

    val productDetailsState = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Page Loader
        if (productDetailsState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(Dimensions.normal_50),
                strokeWidth = Dimensions.normal_5
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {

            // Product Image
            AsyncImage(
                model = productDetailsState.productDetails.thumbnail,
                contentDescription = "product Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimensions.normal_300),
                placeholder = painterResource(id = R.drawable.image_thumbnail)
            )

            HorizontalSpacer(width = Dimensions.normal_15)

            // Brand Name
            Text(
                text = productDetailsState.productDetails.brand,
                modifier = Modifier
                    .padding(horizontal = Dimensions.normal_10),
                fontSize = 15.sp,
                textDecoration = TextDecoration.Underline
            )

            HorizontalSpacer()

            // Product Title
            Text(
                text = productDetailsState.productDetails.title,
                modifier = Modifier
                    .padding(horizontal = Dimensions.normal_10)
                    .padding(bottom = Dimensions.normal_5),
                fontSize = 18.sp
            )

            HorizontalSpacer(width = Dimensions.normal_3)

            // Price
            Text(
                text = productDetailsState.productDetails.price.toPrice(),
                modifier = Modifier
                    .padding(horizontal = Dimensions.normal_10)
                    .padding(bottom = Dimensions.normal_5),
                fontSize = 15.sp,
                color = Color.Red
            )

            HorizontalSpacer(width = Dimensions.normal_3)

            // Product Description

            Text(
                text = stringResource(id = R.string.product_details),
                modifier = Modifier
                    .padding(horizontal = Dimensions.normal_10)
                    .padding(bottom = Dimensions.normal_5),
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )

            HorizontalSpacer(width = Dimensions.normal_3)

            Text(
                text = productDetailsState.productDetails.description,
                modifier = Modifier
                    .padding(horizontal = Dimensions.normal_15)
                    .padding(bottom = Dimensions.normal_5)
                    .fillMaxSize(),
                textAlign = TextAlign.Left,
                fontSize = 15.sp,
            )
        }
    }
}
