package com.example.admob345_x_k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    private lateinit var mAdView: AdView

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
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")
        // Из AS изнутри работает
        mAdView = findViewById(R.id.madView)
        val madRequest = AdRequest.Builder().build()
        mAdView.loadAd(madRequest)


        // Из док Ads Google - создать баннер программно не работает
         val adView = AdView(this)
         adView.adSize = AdSize.BANNER
         adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        //mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        // TODO: Add adView to your view hierarchy. т.е надо добавить adView в XML



    }
}


/* https://stackoverflow.com/questions/52325837/why-my-admob-rewarded-video-ad-failed-to-load-again-and-again

 */