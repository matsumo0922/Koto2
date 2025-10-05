package me.matsumo.koto.core.ui.utils

import android.icu.util.ULocale
import java.util.Locale

actual fun localizedLanguageName(
    languageTag: String,
    displayLocaleTag: String?
): String {
    return try {
        val displayLocale: Locale =
            displayLocaleTag?.let(Locale::forLanguageTag) ?: Locale.getDefault()

        if (languageTag.equals("en-US", ignoreCase = true)) {
            return Locale.ENGLISH.getDisplayName(displayLocale).orEmpty().trim()
        }

        val uDisplay = ULocale.forLocale(displayLocale)
        val uTarget = ULocale.forLanguageTag(languageTag)
        val icuName = uTarget.getDisplayName(uDisplay).orEmpty().trim()

        if (icuName.isNotEmpty() && !icuName.equals(uTarget.toLanguageTag(), ignoreCase = true)) {
            return icuName
        }

        val target = Locale.forLanguageTag(languageTag)
        val name = if (target.script.isNullOrEmpty() && target.country.isNullOrEmpty()) {
            target.getDisplayLanguage(displayLocale)
        } else {
            target.getDisplayName(displayLocale)
        }.orEmpty().trim()

        name.takeIf { it.isNotEmpty() && !it.equals(target.toLanguageTag(), ignoreCase = true) }
            ?: languageTag
    } catch (_: Throwable) {
        languageTag
    }
}