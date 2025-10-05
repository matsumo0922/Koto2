package me.matsumo.koto.core.ui.utils

import me.matsumo.koto.core.model.LanguageCode

expect fun localizedLanguageName(
    languageTag: String,
    displayLocaleTag: String? = null
): String

fun LanguageCode.getDisplayName(): String {
    return localizedLanguageName(this.code)
}