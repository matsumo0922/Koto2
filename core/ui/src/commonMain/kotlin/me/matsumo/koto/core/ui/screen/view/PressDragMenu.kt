package me.matsumo.koto.core.ui.screen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import me.matsumo.koto.core.ui.theme.KotoTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PressDragMenu(
    items: ImmutableList<String>,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    var expanded by remember { mutableStateOf(false) }

    // アンカーと各行の高さ（Collapsed 高=行高）を実測。未計測時のフォールバックは 48.dp。
    var rowHeightPx by remember { mutableFloatStateOf(0f) }
    val rowHeightDp: Dp = if (rowHeightPx > 0f) Dp(rowHeightPx / 1f) else 48.dp

    // アンカー（常時表示）
    Box(
        modifier = modifier
            .widthIn(min = 96.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { expanded = true }
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .height(rowHeightDp)
                .onGloballyPositioned { if (rowHeightPx == 0f) rowHeightPx = it.size.height.toFloat() }
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = items[selectedIndex] + expanded,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }

    if (expanded) {
        Popup(
            popupPositionProvider = remember { AboveAnchorPositionProvider(marginPx = 6) },
            onDismissRequest = { expanded = false },
            properties = PopupProperties(focusable = true) // 外側タップで閉じる
        ) {
            Surface(
                modifier = Modifier.widthIn(min = 96.dp),
                shape = RoundedCornerShape(12.dp),
                tonalElevation = 2.dp,
                shadowElevation = 6.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Column {
                    items.forEachIndexed { index, label ->
                        val selected = index == selectedIndex
                        Box(
                            modifier = Modifier
                                .height(rowHeightDp)
                                .background(
                                    if (selected)
                                        MaterialTheme.colorScheme.primary.copy(alpha = 0.10f)
                                    else
                                        Color.Transparent
                                )
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    selectedIndex = index
                                    expanded = false
                                    onSelect(index)
                                }
                                .padding(horizontal = 12.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (selected) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}

private class AboveAnchorPositionProvider(
    private val marginPx: Int = 8
) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        val desiredX = anchorBounds.left
        val desiredY = anchorBounds.top - popupContentSize.height - marginPx

        val clampedX = desiredX.coerceIn(
            0,
            (windowSize.width - popupContentSize.width).coerceAtLeast(0)
        )
        val clampedY = desiredY.coerceAtLeast(0)

        return IntOffset(clampedX, clampedY)
    }
}

@Preview
@Composable
private fun PressDragMenuPreview() {
    KotoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center,
        ) {
            PressDragMenu(
                modifier = Modifier.size(200.dp),
                items = listOf("あ", "い", "う", "え", "お").toImmutableList(),
                onSelect = {

                }
            )
        }
    }
}
