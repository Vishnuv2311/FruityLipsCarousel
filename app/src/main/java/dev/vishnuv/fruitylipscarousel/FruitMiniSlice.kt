package dev.vishnuv.fruitylipscarousel

import androidx.compose.animation.core.EaseOutElastic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vishnuv.fruitylipscarousel.ui.theme.FruityLipsCarouselTheme

@Composable
fun FruitMiniSlice(
    modifier: Modifier = Modifier,
    index: Int = 0,
    activeFruitIndex: Int = 0
) {
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp
    val height = configuration.screenHeightDp

    val fruitMiniAnimation = animateFloatAsState(
        label = "Fruit Mini Slice Size and Opacity",
        targetValue = if (index == activeFruitIndex) 0f else 1f,
        animationSpec = tween(easing = EaseOutElastic, durationMillis = 200)
    )

    Image(
        painter = painterResource(fruitItemList[index].miniSliceDrawable),
        modifier = Modifier
            .size(width.dp, height.dp)
            .scale(fruitMiniAnimation.value)
            .alpha(fruitMiniAnimation.value.coerceIn(0f, 1f)),
        contentDescription = "fruit slice"
    )

}

@Preview
@Composable
private fun FruitMiniSlicePreview() {
    FruityLipsCarouselTheme {
        FruitMiniSlice()
    }
}