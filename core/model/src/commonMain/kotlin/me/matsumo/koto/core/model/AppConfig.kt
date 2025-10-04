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
) {
    companion object {
        val DEFAULT = AppConfig(
            versionName = "0.0.1",
            versionCode = 1,
            developerPin = "0000",
            adMobAppId = "",
            adMobInterstitialAdUnitId = "",
            adMobBannerAdUnitId = "",
            purchaseAndroidApiKey = null,
            purchaseIosApiKey = null,
        )
    }
}
