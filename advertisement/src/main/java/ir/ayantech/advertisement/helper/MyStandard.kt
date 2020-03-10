package ir.ayantech.advertisement.helper

import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd

typealias SimpleCallback = () -> Unit

typealias InterstitialAdCallBack = (InterstitialAd) -> Unit

typealias NativeAdCallBack = (UnifiedNativeAd) -> Unit

typealias RewardedAdCallBack = (RewardedAd) -> Unit

typealias RewardItemCallBack = (RewardItem) -> Unit

typealias StringCallback = (String) -> Unit

typealias StringReturn = () -> String