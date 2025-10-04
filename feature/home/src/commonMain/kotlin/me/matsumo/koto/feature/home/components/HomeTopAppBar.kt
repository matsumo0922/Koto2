package me.matsumo.koto.feature.home.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import me.matsumo.koto.core.resource.Res
import me.matsumo.koto.core.resource.app_name
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeTopAppBar(
    onHistoryClicked: () -> Unit,
    onAccountClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(Res.string.app_name))

                    addStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                        ),
                        start = 0,
                        end = 4,
                    )
                },
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        navigationIcon = {
            IconButton(onHistoryClicked) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onAccountClicked) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                )
            }
        }
    )
}
