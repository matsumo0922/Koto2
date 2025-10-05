package me.matsumo.koto.core.ui.screen.view

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import kotlinx.collections.immutable.ImmutableList
import kotlin.math.roundToInt

@Composable
fun PressDragMenu(
    items: ImmutableList<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current

    var isMenuOpen by rememberSaveable { mutableStateOf(false) }
    var rowSize by remember { mutableStateOf(IntSize.Zero) }
    var rowOffset by remember { mutableStateOf(IntOffset.Zero) }

    val provider = remember(rowSize, selectedIndex) {
        SelectedAlignedPositionProvider(
            rowHeightPx = rowSize.height,
            rowOffset = rowOffset,
            selectedIndex = selectedIndex,
            paddingPx = with(density) { 8.dp.roundToPx() },
        )
    }

    Box(modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    rowSize = it.size
                    rowOffset = it.positionInWindow().let { position ->
                        IntOffset(position.x.roundToInt(), position.y.roundToInt())
                    }
                }
                .clip(RoundedCornerShape(16.dp))
                .clickable { isMenuOpen = true }
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = items[selectedIndex],
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        if (isMenuOpen) {
            PressDialogMenuPopup(
                provider = provider,
                items = items,
                itemWidth = with(density) { rowSize.width.toDp() },
                selectedIndex = selectedIndex,
                onSelect = onSelect,
                onDismissRequest = { isMenuOpen = false },
            )
        }
    }
}

@Composable
private fun PressDialogMenuPopup(
    provider: PopupPositionProvider,
    itemWidth: Dp,
    items: ImmutableList<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val visibleState = remember { MutableTransitionState(false) }
    val targetScale = if (visibleState.targetState) 1f else 0f
    val scaleY by animateFloatAsState(
        targetValue = targetScale,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = "menu-scaleY"
    )

    val pivotY = if (items.size <= 1) 0.5f else selectedIndex.toFloat() / items.lastIndex.toFloat()

    LaunchedEffect(Unit) {
        visibleState.targetState = true
    }

    Popup(
        onDismissRequest = onDismissRequest,
        popupPositionProvider = provider,
    ) {
        Surface(
            modifier = modifier.graphicsLayer(
                transformOrigin = TransformOrigin(pivotFractionX = 0.5f, pivotFractionY = pivotY),
                scaleX = 1f,      // 横は固定
                scaleY = scaleY,  // 縦だけ伸縮
            ),
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 2.dp,
            shadowElevation = 6.dp,
            color = MaterialTheme.colorScheme.surfaceContainer,
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .graphicsLayer { alpha = (scaleY - 0.75f) * 4f }
            ) {
                for (index in items.indices) {
                    Box(
                        modifier = Modifier
                            .width(itemWidth)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                onSelect.invoke(index)
                                onDismissRequest.invoke()
                            }
                            .background(
                                if (index == selectedIndex) {
                                    MaterialTheme.colorScheme.primaryContainer
                                } else {
                                    MaterialTheme.colorScheme.surfaceContainer
                                }
                            )
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = items[index],
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
            }
        }
    }
}

private class SelectedAlignedPositionProvider(
    private val rowHeightPx: Int,
    private val rowOffset: IntOffset,
    private val selectedIndex: Int,
    private val paddingPx: Int,
) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        return IntOffset(
            x = rowOffset.x - paddingPx,
            y = rowOffset.y - (rowHeightPx * selectedIndex) - paddingPx
        )
    }
}
