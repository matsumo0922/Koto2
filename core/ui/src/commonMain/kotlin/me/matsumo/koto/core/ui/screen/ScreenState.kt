package me.matsumo.koto.core.ui.screen

import androidx.compose.runtime.Stable
import org.jetbrains.compose.resources.StringResource

@Stable
sealed class ScreenState<out T> {
    data object Loading : ScreenState<Nothing>()

    data class Error(
        val message: StringResource,
        val retryTitle: StringResource? = null,
    ) : ScreenState<Nothing>()

    data class Idle<T>(
        var data: T,
    ) : ScreenState<T>()
}
