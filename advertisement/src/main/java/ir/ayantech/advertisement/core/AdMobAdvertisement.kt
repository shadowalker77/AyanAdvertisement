package ir.ayantech.advertisement.core

import android.app.Activity
import android.view.ViewGroup
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import ir.ayantech.advertisement.helper.*

object AdMobAdvertisement {

    private var interstitialAd: InterstitialAd? = null
    private var secondInterstitialAd: InterstitialAd? = null

    internal fun initialize(activity: Activity) {
        MobileAds.initialize(activity)
        requestAdMobInterstitial(
            activity,
            AyanAdvertisementCore.adMobInterstitialUnitID(),
            ready = {
                interstitialAd = it
            })
        requestAdMobInterstitial(
            activity,
            AyanAdvertisementCore.adMobSecondInterstitialUnitID(),
            ready = {
                secondInterstitialAd = it
            })
    }

    fun showInterstitialAd(
        activity: Activity,
        fail: StringCallback? = null,
        opened: SimpleCallback? = null,
        clicked: SimpleCallback? = null,
        leftApplication: SimpleCallback? = null,
        closed: SimpleCallback? = null
    ) {
        var interstitialAdToReturn: InterstitialAd? = null
        when {
            interstitialAd != null -> {
                interstitialAdToReturn = interstitialAd
                interstitialAd = null
                requestAdMobInterstitial(
                    activity,
                    AyanAdvertisementCore.adMobInterstitialUnitID(),
                    ready = {
                        interstitialAd = it
                    })
            }
            secondInterstitialAd != null -> {
                interstitialAdToReturn = secondInterstitialAd
                secondInterstitialAd = null
                requestAdMobInterstitial(
                    activity,
                    AyanAdvertisementCore.adMobSecondInterstitialUnitID(),
                    ready = {
                        secondInterstitialAd = it
                    })
            }
        }
        interstitialAdToReturn?.adListener = simplifiedAdListener(
            null,
            fail,
            opened,
            clicked,
            leftApplication,
            closed
        )
        interstitialAdToReturn?.show()
        if (interstitialAdToReturn == null) {
            fail?.invoke("NoAd")
        }
    }

    private fun requestAdMobInterstitial(
        activity: Activity,
        unitId: String,
        ready: InterstitialAdCallBack
    ) {
        val interstitialAd = InterstitialAd(activity)
        interstitialAd.adUnitId = unitId
        interstitialAd.adListener = simplifiedInterstitialAdListener(
            interstitialAd,
            ready, null, null, null, null, null
        )
        interstitialAd.loadAd(AdRequest.Builder().build())
    }

    fun requestAdMobBanner(
        viewGroup: ViewGroup,
        ready: SimpleCallback? = null,
        fail: StringCallback? = null,
        opened: SimpleCallback? = null,
        clicked: SimpleCallback? = null,
        leftApplication: SimpleCallback? = null,
        closed: SimpleCallback? = null
    ) {
        val adView = AdView(viewGroup.context)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = AyanAdvertisementCore.adMobBannerUnitID()
        adView.adListener = simplifiedAdListener(
            ready,
            fail,
            opened,
            clicked,
            leftApplication,
            closed
        )
        adView.loadAd(AdRequest.Builder().build())
        viewGroup.addView(adView)
    }

    fun requestAdMobNative(
        activity: Activity,
        ready: NativeAdCallBack,
        fail: StringCallback? = null,
        opened: SimpleCallback? = null,
        clicked: SimpleCallback? = null,
        leftApplication: SimpleCallback? = null,
        closed: SimpleCallback? = null
    ) {
        val adLoader = AdLoader.Builder(activity, AyanAdvertisementCore.adMobNativeUnitID())
            .forUnifiedNativeAd { ad: UnifiedNativeAd ->
                ready?.invoke(ad)
            }
            .withAdListener(
                simplifiedNativeAdListener(
                    fail,
                    opened,
                    clicked,
                    leftApplication,
                    closed
                )
            )
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                    .build()
            )
            .build()
        adLoader.loadAd(AdRequest.Builder().build())
    }

    fun requestAdMobRewardedVideo(
        activity: Activity,
        ready: RewardedAdCallBack,
        fail: StringCallback? = null
    ) {
        val rewardedAd = RewardedAd(activity, AyanAdvertisementCore.adMobRewardedVideoUnitID())
        val adLoadCallback = object : RewardedAdLoadCallback() {
            override fun onRewardedAdLoaded() {
                ready(rewardedAd)
            }

            override fun onRewardedAdFailedToLoad(errorCode: Int) {
                fail?.invoke(errorCode.toString())
            }
        }
        rewardedAd.loadAd(AdRequest.Builder().build(), adLoadCallback)
    }

    private fun simplifiedAdListener(
        ready: SimpleCallback?,
        fail: StringCallback?,
        opened: SimpleCallback?,
        clicked: SimpleCallback?,
        leftApplication: SimpleCallback?,
        closed: SimpleCallback?
    ) = object : AdListener() {
        override fun onAdLoaded() {
            ready?.invoke()
        }

        override fun onAdFailedToLoad(errorCode: Int) {
            fail?.invoke(errorCode.toString())
        }

        override fun onAdOpened() {
            opened?.invoke()
        }

        override fun onAdClicked() {
            clicked?.invoke()
        }

        override fun onAdLeftApplication() {
            leftApplication?.invoke()
        }

        override fun onAdClosed() {
            closed?.invoke()
        }
    }

    private fun simplifiedInterstitialAdListener(
        interstitialAd: InterstitialAd,
        ready: InterstitialAdCallBack?,
        fail: StringCallback?,
        opened: SimpleCallback?,
        clicked: SimpleCallback?,
        leftApplication: SimpleCallback?,
        closed: SimpleCallback?
    ) = object : AdListener() {
        override fun onAdLoaded() {
            ready?.invoke(interstitialAd)
        }

        override fun onAdFailedToLoad(errorCode: Int) {
            fail?.invoke(errorCode.toString())
        }

        override fun onAdOpened() {
            opened?.invoke()
        }

        override fun onAdClicked() {
            clicked?.invoke()
        }

        override fun onAdLeftApplication() {
            leftApplication?.invoke()
        }

        override fun onAdClosed() {
            closed?.invoke()
        }
    }

    private fun simplifiedNativeAdListener(
        fail: StringCallback?,
        opened: SimpleCallback?,
        clicked: SimpleCallback?,
        leftApplication: SimpleCallback?,
        closed: SimpleCallback?
    ) = object : AdListener() {

        override fun onAdFailedToLoad(errorCode: Int) {
            fail?.invoke(errorCode.toString())
        }

        override fun onAdOpened() {
            opened?.invoke()
        }

        override fun onAdClicked() {
            clicked?.invoke()
        }

        override fun onAdLeftApplication() {
            leftApplication?.invoke()
        }

        override fun onAdClosed() {
            closed?.invoke()
        }
    }
}