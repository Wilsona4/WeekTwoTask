package com.funcrib.weektwotask

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var lifecycleObserver: MainActivityObserver
    private var TAG : String = this.javaClass.simpleName
    private var portraitCount = 1
    private var landscapeCount = 0
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Initialize the LifeCycle Observer*/
        lifecycleObserver = MainActivityObserver()
        lifecycle.addObserver(lifecycleObserver)


        handler.postDelayed(
            {tvLifecycle.text = "CREATED"},
            1400
        )
        Log.i(TAG, "Activity Created")

        /*Set Default Orientation Text*/
        tvOrientation.text = "Portrait Mode\nPortraitCount = $portraitCount"

        /*Handling Orientation Change with data saved in the saved instance state*/
        if (savedInstanceState != null) {
            /*Initialize orientation variable to app orientation*/
            val orientation = resources.configuration.orientation
            /*Get the data saved in the savedInstanceState via it's key*/
            portraitCount = savedInstanceState.getInt("PORTRAIT_COUNT")
            landscapeCount = savedInstanceState.getInt("LANDSCAPE_COUNT")

            when (orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    /*increment the landscape count and and set text to landscape count*/
                    landscapeCount++
                    tvOrientation.text = "Landscape Mode\nLandScapeCount = $landscapeCount"
                }
                Configuration.ORIENTATION_PORTRAIT -> {
                    /*increment the portrait count and and set text to portrait count*/
                    portraitCount++
                    tvOrientation.text = "Portrait Mode\nPortraitCount = $portraitCount"
                }
            }
        }
        /*Navigate to Fragment Activity*/
        toFragment.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed(
            {tvLifecycle.text = "STARTED"},
            1600
        )
        Log.i(TAG, "Activity Started")
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(
            {tvLifecycle.text = "RESUMED"},
            1800
        )
        Log.i(TAG, "Activity Resumed")
    }

    override fun onPause() {
        super.onPause()
        handler.postDelayed(
            {tvLifecycle.text = "PAUSED"},
            600
        )
        Log.i(TAG, "Activity Paused")
    }

    override fun onStop() {
        super.onStop()
        handler.postDelayed(
            {tvLifecycle.text = "STOPPED"},
            800
        )
        Log.i(TAG, "Activity Stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.postDelayed(
            {tvLifecycle.text = "DESTROYED"},
            1000
        )
        Log.i(TAG, "Activity Destroyed")
    }

    override fun onRestart() {
        super.onRestart()
        super.onStop()
        handler.postDelayed(
            {tvLifecycle.text = "RESTARTED"},
            1200
        )
        Log.i(TAG, "Activity Restarted")
    }

    /*Save Instance State to persist values of Landscape and portrait count*/
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("LANDSCAPE_COUNT", landscapeCount)
        outState.putInt("PORTRAIT_COUNT", portraitCount)
    }
}