package me.matsumo.koto.core.model

expect val currentPlatform: Platform

enum class Platform {
    Android,
    IOS,
    Desktop,
}
