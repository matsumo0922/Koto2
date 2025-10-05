package me.matsumo.koto.core.model

import kotlinx.serialization.Serializable

@Serializable
data class TranslationDirection(
    val source: LanguageCode,
    val target: LanguageCode,
)