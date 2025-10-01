package me.matsumo.koto.feature.setting.components.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.matsumo.koto.core.model.AppSetting
import me.matsumo.koto.core.resource.Res
import me.matsumo.koto.core.resource.setting_information
import me.matsumo.koto.core.resource.setting_information_app_id
import me.matsumo.koto.core.resource.setting_information_app_version
import me.matsumo.koto.core.ui.theme.LocalAppConfig
import me.matsumo.koto.feature.setting.components.SettingTextItem
import me.matsumo.koto.feature.setting.components.SettingTitleItem
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SettingInfoSection(
    setting: AppSetting,
    modifier: Modifier = Modifier,
) {
    val appConfig = LocalAppConfig.current

    Column(modifier) {
        SettingTitleItem(
            modifier = Modifier.fillMaxWidth(),
            text = Res.string.setting_information,
        )

        SettingTextItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(Res.string.setting_information_app_id),
            description = setting.id,
            onClick = null,
        )

        SettingTextItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(Res.string.setting_information_app_version),
            description = "${appConfig.versionName}:${appConfig.versionCode} " + when {
                setting.plusMode && setting.developerMode -> "[P+D]"
                setting.plusMode -> "[Plus]"
                setting.developerMode -> "[Dev]"
                else -> ""
            },
            onClick = { },
        )
    }
}
