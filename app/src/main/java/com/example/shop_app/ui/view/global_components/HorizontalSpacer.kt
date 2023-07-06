package com.example.shop_app.ui.view.global_components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.shop_app.ui.theme.Dimensions

@Composable
fun HorizontalSpacer(width: Dp = Dimensions.normal_5) {
    Spacer(modifier = Modifier.width(width))
}
