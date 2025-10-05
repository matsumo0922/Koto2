package me.matsumo.koto.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toImmutableList
import me.matsumo.koto.core.ui.screen.view.PressDragMenu

@Composable
internal fun HomeBottomBar(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PressDragMenu(
            modifier = Modifier.width(200.dp),
            items = listOf("あ", "い", "う", "え", "お").toImmutableList(),
            selectedIndex = 3,
            onSelect = {

            }
        )

        Spacer(
            Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(Color.Red)
        )
    }
}
