package me.matsumo.koto.core.repository.di

import me.matsumo.koto.core.repository.AppSettingRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AppSettingRepository)
}
