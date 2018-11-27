package com.example.admob345_x_k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;







class MainActivity : AppCompatActivity() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private val TAG = "MainActivity"
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

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }
}
