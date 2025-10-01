package me.matsumo.koto

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import me.matsumo.koto.core.model.Destination
import me.matsumo.koto.core.ui.theme.LocalNavController
import me.matsumo.koto.feature.home.homeScreen
import me.matsumo.koto.feature.setting.oss.settingLicenseScreen
import me.matsumo.koto.feature.setting.settingScreen

@Composable
internal fun AppNavHost(
    modifier: Modifier = Modifier,
) {
    val navController = LocalNavController.current

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.Home,
    ) {
        homeScreen()
        settingScreen()
        settingLicenseScreen()
    }
}
