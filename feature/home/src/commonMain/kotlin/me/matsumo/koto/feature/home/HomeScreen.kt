package me.matsumo.koto.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.matsumo.koto.core.model.Destination
import me.matsumo.koto.core.ui.theme.LocalNavController
import me.matsumo.koto.feature.home.components.HomeTopAppBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    @Suppress("UNUSED_PARAMETER")
    viewModel: HomeViewModel = koinViewModel(),
) {
    val navController = LocalNavController.current

    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                onHistoryClicked = { },
                onAccountClicked = { navController.navigate(Destination.Setting.Root) },
            )
        }
    ) {

    }
}
