package me.matsumo.koto.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import me.matsumo.koto.core.model.LanguageCode
import me.matsumo.koto.core.model.ModelType
import me.matsumo.koto.core.model.TranslationDirection
import me.matsumo.koto.core.ui.screen.view.PressDragMenu

@Composable
internal fun HomeBottomBar(
    modifier: Modifier = Modifier,
) {
    val translationDirection = TranslationDirection(
        source = LanguageCode.ENGLISH_UK,
        target = LanguageCode.ENGLISH_US,
    )

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .navigationBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HomeBottomLanguageSection(
            modifier = Modifier.fillMaxWidth(),
            translationDirection = translationDirection,
            availableSourceLanguages = adjustMenuOrder(
                list = LanguageCode.entries.toImmutableList(),
                selectedItem = translationDirection.source,
            ).toImmutableList(),
            availableTargetLanguages = adjustMenuOrder(
                list = LanguageCode.entries.toImmutableList(),
                selectedItem = translationDirection.target,
            ).toImmutableList(),
            onDirectionChanged = {},
        )

        HomeBottomInputSection(
            modifier = Modifier.fillMaxWidth(),
            selectedModelType = ModelType.Speed,
            onMicClicked = {},
            onClipboardClicked = {},
            onModelTypeClicked = {},
        )
    }
}

private fun <T> adjustMenuOrder(
    list: ImmutableList<T>,
    selectedItem: T,
): List<T> {
    val n = list.size
    val selectedIndex = list.indexOf(selectedItem)

    if (n <= 1) return list.toList()

    require(selectedIndex in 0 until n) { "selectedIndex out of range: $selectedIndex" }

    val s = (selectedIndex + 2) % n
    if (s == 0) return list.toList()

    val out = ArrayList<T>(n)
    for (i in 0 until n) {
        out.add(list[(i + s) % n])
    }
    return out
}
