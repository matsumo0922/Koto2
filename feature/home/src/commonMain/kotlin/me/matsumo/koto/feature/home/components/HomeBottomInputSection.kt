package me.matsumo.koto.feature.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.matsumo.koto.core.model.ModelType
import me.matsumo.koto.core.resource.Res
import me.matsumo.koto.core.resource.home_mic
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun HomeBottomInputSection(
    selectedModelType: ModelType,
    onMicClicked: () -> Unit,
    onClipboardClicked: () -> Unit,
    onModelTypeClicked: (ModelType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val micButtonHeight = ButtonDefaults.LargeContainerHeight

    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterHorizontally,
        ),
    ) {
        Button(
            modifier = Modifier.heightIn(micButtonHeight),
            contentPadding = ButtonDefaults.contentPaddingFor(micButtonHeight),
            onClick = { },
        ) {
            Icon(
                modifier = Modifier.size(ButtonDefaults.iconSizeFor(micButtonHeight)),
                imageVector = Icons.Default.Mic,
                contentDescription = null,
            )

            Spacer(
                modifier = Modifier.size(ButtonDefaults.iconSpacingFor(micButtonHeight))
            )

            Text(stringResource(Res.string.home_mic))
        }
    }
}