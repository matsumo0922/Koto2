package me.matsumo.koto.core.ui.utils

import java.util.Locale

actual fun localizedLanguageName(
    languageTag: String,
    displayLocaleTag: String?
): String {
    return try {
        val displayLocale = displayLocaleTag?.let(Locale::forLanguageTag) ?: Locale.getDefault()
        val target = Locale.forLanguageTag(languageTag)

        val name = if (target.country.isNullOrEmpty() && target.script.isNullOrEmpty()) {
            target.getDisplayLanguage(displayLocale)
        } else {
            target.getDisplayName(displayLocale)
        }

        name.takeIf { it.isNotBlank() } ?: languageTag
    } catch (_: Throwable) {
        languageTag
    }
}