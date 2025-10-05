package me.matsumo.koto.feature.home.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
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
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PressDragMenu(
            modifier = Modifier.weight(1f),
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

            }
        ) {
            Icon(
                imageVector = Icons.Default.SwapHoriz,
                contentDescription = null,
            )
        }

        PressDragMenu(
            modifier = Modifier.weight(1f),
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