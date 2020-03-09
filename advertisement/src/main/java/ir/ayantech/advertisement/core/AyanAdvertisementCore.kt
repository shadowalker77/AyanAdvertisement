package ir.ayantech.advertisement.core

import android.app.Activity

object AyanAdvertisementCore {
    fun initialize(activity: Activity) {
        AdMobAdvertisement.initialize(activity)
        TapsellAdvertisement.initialize(activity)
    }
}