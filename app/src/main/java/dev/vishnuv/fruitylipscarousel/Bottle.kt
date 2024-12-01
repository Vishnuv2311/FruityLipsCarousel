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
fun Bottle(
    modifier: Modifier = Modifier,
    index: Int = 0,
    activeFruitIndex: Int = 0
) {

    val bottleAnimation = animateFloatAsState(
        label = "Bottle Size and Opacity",
        targetValue = if (index == activeFruitIndex) 1.4f else  0.2f,
        animationSpec = tween(easing = EaseOutElastic, durationMillis = 500)
    )

    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp
    val height = configuration.screenHeightDp * 0.6f

    Image(
        painter = painterResource(id = fruitItemList[index].bottleDrawable),
        contentDescription = "bottle",
        modifier = Modifier
            .size(width.dp, height.dp)
            .scale(bottleAnimation.value)
            .alpha(if (index == activeFruitIndex) 1f else 0f)
    )
}

@Preview
@Composable
private fun BottlePreview() {
    FruityLipsCarouselTheme {
        Bottle()
    }
}