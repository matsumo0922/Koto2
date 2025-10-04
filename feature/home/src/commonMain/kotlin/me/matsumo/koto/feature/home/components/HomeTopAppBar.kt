package me.matsumo.koto.feature.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import me.matsumo.koto.core.resource.Res
import me.matsumo.koto.core.resource.app_name1
import me.matsumo.koto.core.resource.app_name2
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
                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append(stringResource(Res.string.app_name1))
                    }

                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 20.sp,
                        )
                    ) {
                        append(" ${stringResource(Res.string.app_name2)}")
                    }
                },
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
