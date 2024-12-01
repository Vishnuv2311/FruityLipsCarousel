package dev.vishnuv.fruitylipscarousel

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import dev.vishnuv.fruitylipscarousel.ui.theme.green
import dev.vishnuv.fruitylipscarousel.ui.theme.greenLight
import dev.vishnuv.fruitylipscarousel.ui.theme.purple
import dev.vishnuv.fruitylipscarousel.ui.theme.purpleLight
import dev.vishnuv.fruitylipscarousel.ui.theme.yellow
import dev.vishnuv.fruitylipscarousel.ui.theme.yellowLight

data class FruitItem(
    val color: Color,
    val lightColor: Color,
    val colorName: String,
    @DrawableRes val sliceDrawable: Int,
    @DrawableRes val bottleDrawable: Int,
    val miniSliceDrawable: Int
)

val fruitItemList = listOf(
    FruitItem(
        color = yellow,
        colorName = "yellow",
        lightColor = yellowLight,
        sliceDrawable = R.drawable.yellow_slice,
        bottleDrawable = R.drawable.yellow_bottle,
        miniSliceDrawable = R.drawable.yellow_mini_slice
    ),
    FruitItem(
        color = green,
        colorName = "green",
        lightColor = greenLight,
        sliceDrawable = R.drawable.green_slice,
        bottleDrawable = R.drawable.green_bottle,
        miniSliceDrawable = R.drawable.green_mini_slice
    ),
    FruitItem(
        color = purple,
        colorName = "purple",
        lightColor = purpleLight,
        sliceDrawable = R.drawable.purple_slice,
        bottleDrawable = R.drawable.purple_bottle,
        miniSliceDrawable = R.drawable.purple_mini_slice
    ),
)