package ir.ayantech.advertisement.core

import android.app.Activity
import android.view.ViewGroup
import ir.ayantech.advertisement.R
import ir.ayantech.advertisement.config.TapsellConfig
import ir.ayantech.advertisement.helper.SimpleCallback
import ir.ayantech.advertisement.helper.StringCallback
import ir.tapsell.plus.AdRequestCallback
import ir.tapsell.plus.AdShowListener
import ir.tapsell.plus.TapsellPlus
import ir.tapsell.plus.TapsellPlusBannerType

object TapsellAdvertisement {
    internal fun initialize(activity: Activity) {
        TapsellPlus.initialize(activity, activity.resources.getString(R.string.tapsell_api_key))
    }

    fun requestVideoAds(
        activity: Activity,
        ready: SimpleCallback,
        fail: StringCallback? = null
    ) {
        TapsellPlus.requestRewardedVideo(
            activity,
            TapsellConfig.getTapsellRewardedVideoZoneID(),
            object : AdRequestCallback() {
                override fun response() {
                    super.response()
                    ready?.invoke()
                }

                override fun error(message: String?) {
                    super.error(message)
                    fail?.invoke(message ?: "")
                }
            })
    }

    fun showVideoAds(
        activity: Activity,
        opened: SimpleCallback? = null,
        closed: SimpleCallback? = null,
        rewarded: SimpleCallback? = null,
        fail: StringCallback? = null
    ) {
        TapsellPlus.showAd(activity, TapsellConfig.getTapsellRewardedVideoZoneID(), object :
            AdShowListener() {
            override fun onClosed() {
                closed?.invoke()
            }

            override fun onOpened() {
                opened?.invoke()
            }

            override fun onRewarded() {
                rewarded?.invoke()
            }

            override fun onError(p0: String?) {
                fail?.invoke(p0 ?: "")
            }
        })
    }

    fun requestInterstitialAds(
        activity: Activity,
        ready: SimpleCallback,
        fail: StringCallback? = null
    ) {
        TapsellPlus.requestInterstitial(
            activity,
            TapsellConfig.getTapsellInterstitialZoneID(),
            object : AdRequestCallback() {
                override fun response() {
                    ready.invoke()
                }

                override fun error(message: String?) {
                    fail?.invoke(message ?: "")
                }
            })
    }

    fun showInterstitialAds(
        activity: Activity,
        opened: SimpleCallback? = null,
        closed: SimpleCallback? = null,
        rewarded: SimpleCallback? = null,
        fail: StringCallback? = null
    ) {
        TapsellPlus.showAd(activity, TapsellConfig.getTapsellInterstitialZoneID(), object :
            AdShowListener() {
            override fun onClosed() {
                closed?.invoke()
            }

            override fun onOpened() {
                opened?.invoke()
            }

            override fun onRewarded() {
                rewarded?.invoke()
            }

            override fun onError(p0: String?) {
                fail?.invoke(p0 ?: "")
            }
        })
    }

    fun showBannerAds(
        activity: Activity,
        viewGroup: ViewGroup,
        ready: SimpleCallback? = null,
        fail: StringCallback? = null
    ) {
        TapsellPlus.showBannerAd(activity,
            viewGroup,
            TapsellConfig.getTapsellBannerZoneID(),
            TapsellPlusBannerType.BANNER_320x50,
            object :
                AdRequestCallback() {
                override fun response() {
                    ready?.invoke()
                }

                override fun error(p0: String?) {
                    fail?.invoke(p0 ?: "")
                }
            })
    }
}