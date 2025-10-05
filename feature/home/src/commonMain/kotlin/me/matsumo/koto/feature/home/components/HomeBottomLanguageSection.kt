package me.matsumo.koto.feature.home.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.matsumo.koto.core.model.LanguageCode
import me.matsumo.koto.core.model.TranslationDirection
import me.matsumo.koto.core.ui.screen.view.PressDragMenu
import me.matsumo.koto.core.ui.utils.getDisplayName

@Composable
internal fun HomeBottomLanguageSection(
    translationDirection: TranslationDirection,
    availableSourceLanguages: ImmutableList<LanguageCode>,
    availableTargetLanguages: ImmutableList<LanguageCode>,
    onDirectionChanged: (TranslationDirection) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    var isAnimating by remember { mutableStateOf(false) }

    val leftOffset = remember { Animatable(0f) }
    val rightOffset = remember { Animatable(0f) }
    val leftAlpha = remember { Animatable(1f) }
    val rightAlpha = remember { Animatable(1f) }

    val density = LocalDensity.current
    val travelPx = with(density) { 64.dp.toPx() }

    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PressDragMenu(
            modifier = Modifier
                .weight(1f)
                .graphicsLayer {
                    translationX = leftOffset.value
                    alpha = leftAlpha.value
                },
            items = availableTargetLanguages.map { it.getDisplayName() }.toImmutableList(),
            selectedIndex = availableTargetLanguages.indexOf(translationDirection.target),
            onSelect = {
                onDirectionChanged.invoke(
                    TranslationDirection(
                        source = translationDirection.source,
                        target = availableTargetLanguages[it],
                    )
                )
            }
        )

        IconButton(
            modifier = Modifier.size(32.dp),
            onClick = {
                if (isAnimating) return@IconButton
                isAnimating = true
                scope.launch {
                    val outDuration = 120
                    val inDuration = 160
                    val gap = 60L

                    val leftOut = launch {
                        leftAlpha.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(outDuration, easing = FastOutLinearInEasing)
                        )
                    }
                    val rightOut = launch {
                        rightAlpha.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(outDuration, easing = FastOutLinearInEasing)
                        )
                    }
                    val leftMoveOut = launch {
                        leftOffset.animateTo(
                            targetValue = +travelPx,
                            animationSpec = tween(outDuration, easing = FastOutLinearInEasing)
                        )
                    }
                    val rightMoveOut = launch {
                        rightOffset.animateTo(
                            targetValue = -travelPx,
                            animationSpec = tween(outDuration, easing = FastOutLinearInEasing)
                        )
                    }

                    leftOut.join()
                    rightOut.join()
                    leftMoveOut.join()
                    rightMoveOut.join()

                    onDirectionChanged(
                        TranslationDirection(
                            source = translationDirection.target,
                            target = translationDirection.source,
                        )
                    )

                    delay(gap)

                    leftOffset.snapTo(travelPx)
                    rightOffset.snapTo(-travelPx)
                    leftAlpha.snapTo(0f)
                    rightAlpha.snapTo(0f)

                    val leftIn = launch {
                        leftAlpha.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(inDuration, easing = LinearOutSlowInEasing)
                        )
                    }
                    val rightIn = launch {
                        rightAlpha.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(inDuration, easing = LinearOutSlowInEasing)
                        )
                    }
                    val leftMoveIn = launch {
                        leftOffset.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(inDuration, easing = LinearOutSlowInEasing)
                        )
                    }
                    val rightMoveIn = launch {
                        rightOffset.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(inDuration, easing = LinearOutSlowInEasing)
                        )
                    }

                    leftIn.join()
                    rightIn.join()
                    leftMoveIn.join()
                    rightMoveIn.join()

                    isAnimating = false
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.SwapHoriz,
                contentDescription = null,
            )
        }

        PressDragMenu(
            modifier = Modifier
                .weight(1f)
                .graphicsLayer {
                    translationX = rightOffset.value
                    alpha = rightAlpha.value
                },
            items = availableSourceLanguages.map { it.getDisplayName() }.toImmutableList(),
            selectedIndex = availableSourceLanguages.indexOf(translationDirection.source),
            onSelect = {
                onDirectionChanged.invoke(
                    TranslationDirection(
                        source = availableSourceLanguages[it],
                        target = translationDirection.target,
                    )
                )
            }
        )
    }
}