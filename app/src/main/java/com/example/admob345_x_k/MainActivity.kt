package com.example.admob345_x_k


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import com.google.firebase.analytics.FirebaseAnalytics

import kotlinx.android.synthetic.main.activity_main.* // доступ к полям Layout



 class MainActivity : AppCompatActivity(), RewardedVideoAdListener {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    //private lateinit var mAdView: AdView  // sugar
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var mRewardedVideoAd: RewardedVideoAd


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id3451")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name3451")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        //MobileAds.initialize(this, "YOUR_ADMOB_APP_ID")
        MobileAds.initialize(this, getString(R.string.app_ad_unit_id_0))    //"ca-app-pub-3769175412391280~9052085715")
        // Баннер Из AS изнутри работает
        //mAdView = findViewById(R.id.madView)
        val madRequest = AdRequest.Builder().build()
        //mAdView.loadAd(madRequest)
        madView.loadAd(madRequest)  // sugar


        //Баннер Из док Ads Google - создать баннер программно работает "ca-app-pub-3940256099942544/6300978111"
        val adView = AdView(this)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = getString(R.string.banner_ad_unit_id_0)     //"ca-app-pub-3769175412391280~9052085715")
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        // TODO: Add adView to your view hierarchy. т.е надо добавить adView в XML
        //val mainLay = findViewById<ConstraintLayout>(R.id.container)
        //mainLay.addView(adView)
        container.addView(adView)  // sugar
        // На экране появляется второй баннер (верхний) и в него тоже выводится тестовый баннер

        // Межстраничные объявления
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(R.string.Interstitial_ad_unit_id_0)
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        mMyButton.setOnClickListener {
            mInterstitialAd.loadAd(AdRequest.Builder().build())
            if (mInterstitialAd.isLoaded) mInterstitialAd.show()
            else Toast.makeText(applicationContext, "The interstitial wasn't loaded yet.", Toast.LENGTH_SHORT).show()
        }

        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.rewardedVideoAdListener = this
        loadRewardedVideoAd()

        vMyButton.setOnClickListener {
            if (mRewardedVideoAd.isLoaded)  mRewardedVideoAd.show()
            else Toast.makeText(applicationContext, "The interstitial wasn't loaded yet.", Toast.LENGTH_SHORT).show()
        }
    }
        private fun loadRewardedVideoAd() {
            mRewardedVideoAd.loadAd(
                "ca-app-pub-3940256099942544/5224354917",
                AdRequest.Builder().build()
            )

        }
     override fun onRewarded(reward: RewardItem) {
         Toast.makeText(this, "onRewarded! currency: ${reward.type} amount: ${reward.amount}",
             Toast.LENGTH_SHORT).show()
         // Reward the user.
     }

     override fun onRewardedVideoAdLeftApplication() {
         Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show()
     }

     override fun onRewardedVideoAdClosed() {
         Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show()
     }

     override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
         Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show()
     }

     override fun onRewardedVideoAdLoaded() {
         Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show()
     }

     override fun onRewardedVideoAdOpened() {
         Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show()
     }

     override fun onRewardedVideoStarted() {
         Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show()
     }

     override fun onRewardedVideoCompleted() {
         Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show()
     }

}


/* https://stackoverflow.com/questions/52325837/why-my-admob-rewarded-video-ad-failed-to-load-again-and-again

 */