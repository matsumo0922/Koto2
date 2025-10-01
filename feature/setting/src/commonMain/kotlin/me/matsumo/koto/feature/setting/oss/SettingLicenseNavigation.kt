package me.matsumo.koto.feature.setting.oss

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import me.matsumo.koto.core.model.Destination

fun NavGraphBuilder.settingLicenseScreen() {
    composable<Destination.Setting.License> {
        SettingLicenseRoute(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
