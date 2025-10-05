package me.matsumo.koto.core.ui.utils

import platform.Foundation.NSLocale
import platform.Foundation.autoupdatingCurrentLocale
import platform.Foundation.localeWithLocaleIdentifier
import platform.Foundation.localizedStringForLanguageCode

actual fun localizedLanguageName(
    languageTag: String,
    displayLocaleTag: String?
): String {
    return try {
        val displayLocale = displayLocaleTag
            ?.let { NSLocale.localeWithLocaleIdentifier(it) }
            ?: NSLocale.autoupdatingCurrentLocale()

        val langOnly = languageTag.substringBefore('-')
        val primary = displayLocale.localizedStringForLanguageCode(langOnly)

        val fallbackDetail = if (languageTag.contains('-')) " ($languageTag)" else ""

        (primary ?: languageTag) + fallbackDetail.takeIf { primary == null }
    } catch (_: Throwable) {
        languageTag
    }
}