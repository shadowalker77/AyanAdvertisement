package ir.ayantech.ayanadvertisement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ir.ayantech.advertisement.core.AdMobAdvertisement
import ir.ayantech.advertisement.core.AyanAdvertisementCore
import ir.ayantech.advertisement.core.TapsellAdvertisement
import ir.ayantech.advertisement.helper.simpleShow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AyanAdvertisementCore.initialize(this)

        adMobInterstitial.setOnClickListener {
            AdMobAdvertisement.requestAdMobInterstitial(this,
                ready = {
                    it.show()
                })
        }

        adMobRewardedVideo.setOnClickListener {
            AdMobAdvertisement.requestAdMobRewardedVideo(this,
                ready = {
                    it.simpleShow(this)
                })
        }

        adMobStandardBanner.setOnClickListener {
            AdMobAdvertisement.requestAdMobBanner(adViewContainer,
                fail = {
                    Log.d("adFail", it)
                })
        }

        tapsellInterstitial.setOnClickListener {
            TapsellAdvertisement.requestInterstitialAds(this,
                ready = {
                    TapsellAdvertisement.showInterstitialAds(this)
                })
        }

        tapsellRewardedVideo.setOnClickListener {
            TapsellAdvertisement.requestVideoAds(this,
                ready = {
                    TapsellAdvertisement.showVideoAds(this)
                })
        }

        tapsellStandardBanner.setOnClickListener {
            TapsellAdvertisement.showBannerAds(this, tapsellViewContainer)
        }
    }
}