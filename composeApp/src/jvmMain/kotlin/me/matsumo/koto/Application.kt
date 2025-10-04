package me.matsumo.koto

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.matsumo.koto.core.repository.AppSettingRepository
import org.koin.compose.koinInject

fun main() = application {
    val settingRepository = koinInject<AppSettingRepository>()
    val userData by settingRepository.setting.collectAsStateWithLifecycle(null)

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
