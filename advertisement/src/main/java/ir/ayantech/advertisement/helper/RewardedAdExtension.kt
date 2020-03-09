package ir.ayantech.advertisement.helper

import android.app.Activity
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback

fun RewardedAd.simpleShow(
    activity: Activity,
    opened: SimpleCallback? = null,
    closed: SimpleCallback? = null,
    earnReward: RewardItemCallBack? = null,
    fail: StringCallback? = null
) {
    val adCallback = object : RewardedAdCallback() {
        override fun onRewardedAdOpened() {
            opened?.invoke()
        }

        override fun onRewardedAdClosed() {
            closed?.invoke()
        }

        override fun onUserEarnedReward(reward: RewardItem) {
            earnReward?.invoke(reward)
        }

        override fun onRewardedAdFailedToShow(errorCode: Int) {
            fail?.invoke(errorCode.toString())
        }
    }
    this.show(activity, adCallback)
}