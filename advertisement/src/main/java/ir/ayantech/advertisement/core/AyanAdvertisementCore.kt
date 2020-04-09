package ir.ayantech.advertisement.core

import android.app.Activity
import ir.ayantech.advertisement.helper.StringReturn

object AyanAdvertisementCore {

    var tapsellRewardedVideoZoneID: StringReturn = { "5cfaa802e8d17f0001ffb28e" }
    var tapsellInterstitialZoneID: StringReturn = { "5cfaa942e8d17f0001ffb292" }
    var tapsellBannerZoneID: StringReturn = { "5cfaaa30e8d17f0001ffb294" }

    var adMobBannerUnitID: StringReturn = { "ca-app-pub-3940256099942544/6300978111" }
    var adMobInterstitialUnitID: StringReturn = { "ca-app-pub-3940256099942544/1033173712" }
    var adMobSecondInterstitialUnitID: StringReturn = { "ca-app-pub-3940256099942544/1033173712" }
    var adMobNativeUnitID: StringReturn = { "ca-app-pub-3940256099942544/2247696110" }
    var adMobRewardedVideoUnitID: StringReturn = { "ca-app-pub-3940256099942544/5224354917" }

    private var tapsellApiKey: StringReturn = { "alsoatsrtrotpqacegkehkaiieckldhrgsbspqtgqnbrrfccrtbdomgjtahflchkqtqosa" }

    fun initialize(
        activity: Activity,
        tapsellRewardedVideoZoneID: StringReturn? = null,
        tapsellInterstitialZoneID: StringReturn? = null,
        tapsellBannerZoneID: StringReturn? = null,
        tapsellApiKey: StringReturn? = null,
        adMobBannerUnitID: StringReturn? = null,
        adMobInterstitialUnitID: StringReturn? = null,
        adMobSecondInterstitialUnitID: StringReturn? = null,
        adMobNativeUnitID: StringReturn? = null,
        adMobRewardedVideoUnitID: StringReturn? = null
    ) {
        tapsellRewardedVideoZoneID?.let { this.tapsellRewardedVideoZoneID = it }
        tapsellInterstitialZoneID?.let { this.tapsellInterstitialZoneID = it }
        tapsellBannerZoneID?.let { this.tapsellBannerZoneID = it }

        tapsellApiKey?.let { this.tapsellApiKey = it }

        adMobBannerUnitID?.let { this.adMobBannerUnitID = it }
        adMobInterstitialUnitID?.let { this.adMobInterstitialUnitID = it }
        adMobSecondInterstitialUnitID?.let { this.adMobSecondInterstitialUnitID = it }
        adMobNativeUnitID?.let { this.adMobNativeUnitID = it }
        adMobRewardedVideoUnitID?.let { this.adMobRewardedVideoUnitID = it }

        AdMobAdvertisement.initialize(activity)
        TapsellAdvertisement.initialize(activity, tapsellApiKey())
    }
}