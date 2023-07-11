package com.example.shop_app.ui.view.global_components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.shop_app.ui.theme.Dimensions

/**
 * Creates a horizontal space in the view.
 * @param width - Defines the space to be added in dp.
 *
 * @sample
 *
 * @HorizontalSpacer(Dimensions.normal_10)
 * Creates a Spacer with 10.dp width
 *
 * if width not specified
 * Creates a Spacer with 5.dp width
 */
@Composable
fun HorizontalSpacer(width: Dp = Dimensions.normal_5) {
    Spacer(modifier = Modifier.width(width))
}
