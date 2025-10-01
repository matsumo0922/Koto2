package me.matsumo.koto

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import io.github.vinceglb.filekit.coil.addPlatformFileSupport
import me.matsumo.koto.core.model.AppSetting
import me.matsumo.koto.core.ui.theme.KotoTheme

@Composable
internal fun KotoApp(
    setting: AppSetting,
    modifier: Modifier = Modifier,
) {
    SetupCoil()

    KotoTheme(setting) {
        AppNavHost(modifier)
    }
}

@Composable
private fun SetupCoil() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                addPlatformFileSupport()
            }
            .build()
    }
}
