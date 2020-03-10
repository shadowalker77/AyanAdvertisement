package ir.ayantech.advertisement.core

import android.app.Activity
import ir.ayantech.advertisement.helper.StringReturn

object AyanAdvertisementCore {

    var tapsellRewardedVideoZoneID: StringReturn = { "5cfaa802e8d17f0001ffb28e" }
    var tapsellInterstitialZoneID: StringReturn = { "5cfaa942e8d17f0001ffb292" }
    var tapsellBannerZoneID: StringReturn = { "5cfaaa30e8d17f0001ffb294" }

    var adMobBannerUnitID: StringReturn = { "ca-app-pub-3940256099942544/6300978111" }
    var adMobInterstitialUnitID: StringReturn = { "ca-app-pub-3940256099942544/1033173712" }
    var adMobNativeUnitID: StringReturn = { "ca-app-pub-3940256099942544/2247696110" }
    var adMobRewardedVideoUnitID: StringReturn = { "ca-app-pub-3940256099942544/5224354917" }

    fun initialize(
        activity: Activity,
        tapsellRewardedVideoZoneID: StringReturn? = null,
        tapsellInterstitialZoneID: StringReturn? = null,
        tapsellBannerZoneID: StringReturn? = null,
        adMobBannerUnitID: StringReturn? = null,
        adMobInterstitialUnitID: StringReturn? = null,
        adMobNativeUnitID: StringReturn? = null,
        adMobRewardedVideoUnitID: StringReturn? = null
    ) {
        AdMobAdvertisement.initialize(activity)
        TapsellAdvertisement.initialize(activity)
        tapsellRewardedVideoZoneID?.let { this.tapsellRewardedVideoZoneID = it }
        tapsellInterstitialZoneID?.let { this.tapsellInterstitialZoneID = it }
        tapsellBannerZoneID?.let { this.tapsellBannerZoneID = it }
        adMobBannerUnitID?.let { this.adMobBannerUnitID = it }
        adMobInterstitialUnitID?.let { this.adMobInterstitialUnitID = it }
        adMobNativeUnitID?.let { this.adMobNativeUnitID = it }
        adMobRewardedVideoUnitID?.let { this.adMobRewardedVideoUnitID = it }
    }
}