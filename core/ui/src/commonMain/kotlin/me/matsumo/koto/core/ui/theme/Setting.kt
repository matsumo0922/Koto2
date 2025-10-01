package me.matsumo.koto.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import me.matsumo.koto.core.model.AppConfig
import me.matsumo.koto.core.model.AppSetting

val LocalAppSetting = staticCompositionLocalOf {
    AppSetting.DEFAULT
}

val LocalAppConfig = staticCompositionLocalOf<AppConfig> {
    error("No AppConfig provided")
}
