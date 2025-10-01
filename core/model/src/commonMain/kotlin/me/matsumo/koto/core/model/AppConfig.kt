package me.matsumo.koto.core.model

data class AppConfig(
    val versionName: String,
    val versionCode: Int,
    val developerPin: String,
    val adMobAppId: String,
    val adMobInterstitialAdUnitId: String,
    val adMobBannerAdUnitId: String,
    val purchaseAndroidApiKey: String?,
    val purchaseIosApiKey: String?,
)
