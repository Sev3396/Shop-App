package com.example.shop_app.ui.view.global_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.shop_app.R
import com.example.shop_app.data.repository.db.entity.RecentlyViewProducts
import com.example.shop_app.ui.theme.Dimensions

@Composable
fun RecentlyViewedProducts(
    modifier: Modifier,
    productList: List<RecentlyViewProducts>,
    onClearClick: () -> Unit,
    onItemClick: ((productId: String) -> Unit)?,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.normal_10),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.recently_viewed_products),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.clear_all),
                fontSize = 15.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickableWithoutRipple {
                        onClearClick.invoke()
                    }
            )
        }

        HorizontalSpacer(width = Dimensions.normal_10)

        LazyRow(
            modifier = Modifier
                .padding(horizontal = Dimensions.normal_10)
        ) {

            items(productList) { products ->
                ProductItemCard(
                    product = products.product,
                    onItemClick = onItemClick,
                    cardWidth = Dimensions.normal_200
                )
            }
        }
    }
}

fun Modifier.clickableWithoutRipple(
    onClick: () -> Unit
): Modifier = composed {
    clickable(
        onClick = onClick,
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    )
}
