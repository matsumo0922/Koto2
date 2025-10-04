package me.matsumo.koto

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import me.matsumo.koto.core.repository.AppSettingRepository
import me.matsumo.koto.di.applyModules
import org.koin.compose.koinInject
import org.koin.core.context.startKoin

fun main() = application {
    initNapier()
    initKoin()

    val settingRepository = koinInject<AppSettingRepository>()
    val userData by settingRepository.setting.collectAsState(null)

    Window(
        title = "koto",
        onCloseRequest = ::exitApplication,
    ) {
        Crossfade(targetState = userData) { data ->
            data?.let {
                KotoApp(
                    modifier = Modifier.fillMaxSize(),
                    setting = it,
                )
            }
        }
    }
}

private fun initKoin() {
    startKoin {
        applyModules()
    }
}

private fun initNapier() {
    Napier.base(DebugAntilog())
}
