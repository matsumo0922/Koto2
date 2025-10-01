package me.matsumo.koto.core.repository

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.StateFlow
import me.matsumo.koto.core.datasource.AppSettingDataSource
import me.matsumo.koto.core.model.AppSetting
import me.matsumo.koto.core.model.Theme

class AppSettingRepository(
    private val dataSource: AppSettingDataSource,
) {
    val setting: StateFlow<AppSetting> = dataSource.setting

    suspend fun setId(id: String) = dataSource.setId(id)

    suspend fun setTheme(theme: Theme) = dataSource.setTheme(theme)

    suspend fun setUseDynamicColor(useDynamicColor: Boolean) = dataSource.setUseDynamicColor(useDynamicColor)

    suspend fun setSeedColor(color: Color) = dataSource.setSeedColor(color)

    suspend fun setPlusMode(plusMode: Boolean) = dataSource.setPlusMode(plusMode)

    suspend fun setDeveloperMode(developerMode: Boolean) = dataSource.setDeveloperMode(developerMode)
}
