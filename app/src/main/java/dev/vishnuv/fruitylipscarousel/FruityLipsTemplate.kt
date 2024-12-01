package dev.vishnuv.fruitylipscarousel

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vishnuv.fruitylipscarousel.ui.theme.FruityLipsCarouselTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FruityLipsTemplate(modifier: Modifier = Modifier) {

    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp

    val pagerState = rememberPagerState(pageCount = { fruitItemList.size })


    val activeBgIndex = pagerState.currentPage
    var activeFruitIndex by remember { mutableIntStateOf(0) }

    val bgColor1Animation = animateColorAsState(
        label = "Background Color",
        targetValue = fruitItemList[activeBgIndex].color,
        animationSpec = tween(durationMillis = 700)
    )

    val bgColor2Animation = animateColorAsState(
        label = "Background Color",
        targetValue = fruitItemList[activeBgIndex].lightColor.copy(alpha = 0.9f),
        animationSpec = tween(durationMillis = 700)
    )

    val centerColorAnimation = animateColorAsState(
        label = "Center Color",
        targetValue = fruitItemList[activeBgIndex].color.copy(alpha = 0.2f),
        animationSpec = tween(durationMillis = 700)
    )

    var scrollDirection by remember { mutableStateOf(ScrollDirection.Idle) }


    val hasStartedScroll = pagerState.interactionSource.collectIsDraggedAsState()
    val isScrolling = pagerState.isScrollInProgress

    if (!hasStartedScroll.value && !isScrolling) {
        activeFruitIndex = pagerState.currentPage
    }

    scrollDirection = when {
        activeBgIndex > pagerState.targetPage -> ScrollDirection.Forward
        activeBgIndex < pagerState.targetPage -> ScrollDirection.Reverse
        else -> ScrollDirection.Idle
    }

    Scaffold { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            bgColor1Animation.value,
                            bgColor2Animation.value
                        ),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                )
                .padding(innerPadding)
        ) {

            Box(
                Modifier
                    .size(width = (width / 2).dp, height = (width / 2).dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.5f),
                                centerColorAnimation.value.copy(alpha = 0.1f)
                            ),

                            ),
                        shape = CircleShape
                    )
                    .align(Alignment.Center)
            )

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
            )

            HorizontalPager(
                state = pagerState,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(contentAlignment = Alignment.BottomCenter) {
                    Bottle(
                        index = activeBgIndex,
                        activeFruitIndex = activeFruitIndex
                    )
                    FruitMiniSlice(
                        index = activeBgIndex,
                        activeFruitIndex = activeFruitIndex
                    )
                    FruitSlice(
                        index = activeBgIndex,
                        activeFruitIndex = activeFruitIndex,
                        scrollDirection = scrollDirection,
                        isScrolling = isScrolling
                    )

                }

            }


        }
    }
}


@Preview
@Composable
private fun FruityLipsTemplatePreview() {
    FruityLipsCarouselTheme {
        FruityLipsTemplate()
    }
}