package me.matsumo.koto.feature.setting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.matsumo.koto.core.model.Destination
import me.matsumo.koto.core.ui.theme.LocalNavController
import me.matsumo.koto.feature.setting.components.SettingTopAppBar
import me.matsumo.koto.feature.setting.components.section.SettingInfoSection
import me.matsumo.koto.feature.setting.components.section.SettingOthersSection
import me.matsumo.koto.feature.setting.components.section.SettingThemeSection
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SettingScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingViewModel = koinViewModel(),
) {
    val navController = LocalNavController.current
    val uriHandler = LocalUriHandler.current
    val setting by viewModel.setting.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            SettingTopAppBar(
                onBackClicked = navController::popBackStack,
                modifier = Modifier,
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = it,
        ) {
            item {
                SettingThemeSection(
                    modifier = Modifier.fillMaxWidth(),
                    setting = setting,
                    onThemeChanged = viewModel::setTheme,
                    onUseDynamicColorChanged = viewModel::setUseDynamicColor,
                    onSeedColorChanged = viewModel::setSeedColor,
                )
            }

            item {
                SettingInfoSection(
                    modifier = Modifier.fillMaxWidth(),
                    setting = setting,
                )
            }

            item {
                SettingOthersSection(
                    modifier = Modifier.fillMaxWidth(),
                    setting = setting,
                    onTeamsOfServiceClicked = {
                        uriHandler.openUri("https://www.matsumo.me/application/all/team_of_service")
                    },
                    onPrivacyPolicyClicked = {
                        uriHandler.openUri("https://www.matsumo.me/application/all/privacy_policy")
                    },
                    onOpenSourceLicenseClicked = {
                        navController.navigate(Destination.Setting.License)
                    },
                    onDeveloperModeChanged = viewModel::setDeveloperMode,
                )
            }
        }
    }
}
