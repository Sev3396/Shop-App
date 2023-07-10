package com.example.shop_app.ui.view.global_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shop_app.R
import com.example.shop_app.components.Utils.toPrice
import com.example.shop_app.domain.model.Product
import com.example.shop_app.ui.theme.Dimensions

/**
 * Used to create Product Item Card view.
 * @param product - Defines the space to be added in dp.
 * @param cardWidth - Defines the width of the Card.
 * @param onItemClick - Returns product id of clicked item.
 *
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductItemCard(
    product: Product,
    cardWidth: Dp,
    onItemClick: ((productId: String) -> Unit)?
) {
    Card(
        elevation = Dimensions.normal_5,
        shape = RoundedCornerShape(Dimensions.normal_5),
        modifier = Modifier
            .width(cardWidth)
            .padding(Dimensions.normal_10),
        onClick = {
            onItemClick?.let {
                it(product.id.toString())
            }
        }
    ) {
        Column(
            modifier = Modifier
                .background(Color.Transparent),
        ) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = "product Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(Dimensions.normal_150),
                placeholder = painterResource(id = R.drawable.image_thumbnail)
            )

            HorizontalSpacer()
            Text(
                text = product.title,
                modifier = Modifier
                    .padding(horizontal = Dimensions.normal_10),
                fontSize = 15.sp,
                maxLines = 1
            )
            HorizontalSpacer()
            Text(
                text = product.brand,
                modifier = Modifier
                    .padding(horizontal = Dimensions.normal_10)
                    .padding(bottom = Dimensions.normal_3)
                    .fillMaxSize(),
                fontSize = 12.sp,
                textDecoration = TextDecoration.Underline
            )

            HorizontalSpacer(width = Dimensions.normal_3)
            Text(
                text = product.price.toPrice(),
                modifier = Modifier
                    .padding(horizontal = Dimensions.normal_10)
                    .padding(bottom = Dimensions.normal_5)
                    .fillMaxSize(),
                fontSize = 13.sp,
                color = Color.Red
            )
        }
    }
}
