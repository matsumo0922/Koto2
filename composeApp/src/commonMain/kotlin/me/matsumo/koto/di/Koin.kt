package me.matsumo.koto.di

import me.matsumo.koto.core.common.di.commonModule
import me.matsumo.koto.core.datasource.di.dataSourceModule
import me.matsumo.koto.core.repository.di.repositoryModule
import me.matsumo.koto.feature.home.di.homeModule
import me.matsumo.koto.feature.setting.di.settingModule
import org.koin.core.KoinApplication

fun KoinApplication.applyModules() {
    modules(appModule)

    modules(commonModule)
    modules(dataSourceModule)
    modules(repositoryModule)

    modules(homeModule)
    modules(settingModule)
}
