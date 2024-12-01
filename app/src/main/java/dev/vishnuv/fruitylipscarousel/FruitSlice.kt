package dev.vishnuv.fruitylipscarousel

import androidx.compose.animation.core.EaseInBack
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vishnuv.fruitylipscarousel.ui.theme.FruityLipsCarouselTheme

@Composable
fun FruitSlice(
    modifier: Modifier = Modifier,
    index: Int = 0,
    activeFruitIndex: Int = 0,
    isScrolling: Boolean = false,
    scrollDirection: ScrollDirection = ScrollDirection.Idle
) {
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp
    val height = configuration.screenHeightDp

    val fruitAnimation = animateFloatAsState(
        label = "Fruit Slice Size",
        targetValue = if (isScrolling && activeFruitIndex == index) 1.5f else 0f,
        animationSpec = tween(durationMillis = 200, easing = EaseInBack)
    )

    val condition = scrollDirection == ScrollDirection.Reverse && activeFruitIndex == index
    val newIndex = if (condition) (activeFruitIndex + 1).coerceIn(
        0,
        fruitItemList.size - 1
    ) else (activeFruitIndex - 1).coerceIn(
        0,
        fruitItemList.size - 1
    )

    Image(
        painter = painterResource(id = fruitItemList[newIndex].sliceDrawable),
        contentDescription = "fruit slice",
        modifier = Modifier
            .graphicsLayer {
                scaleX = if (isScrolling && activeFruitIndex == index) 1f else fruitAnimation.value
                scaleY = if (isScrolling && activeFruitIndex == index) 1f else fruitAnimation.value
                translationX = if (isScrolling) {
                    if (scrollDirection == ScrollDirection.Forward) -width * 0.8f else width * 0.8f
                } else {
                    0f
                }
            }
            .size(width.dp, height.dp)
    )
}

@Preview
@Composable
private fun FruitSlicePreview() {
    FruityLipsCarouselTheme {
        FruitSlice()
    }
}