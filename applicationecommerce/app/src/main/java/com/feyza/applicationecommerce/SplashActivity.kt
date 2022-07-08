package com.feyza.applicationecommerce

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer =   object : CountDownTimer(1800, 3000) {
            override fun onFinish() {
                val intent = Intent(this@SplashActivity, RegisterActivity::class.java)
                startActivity(intent)

            }

            override fun onTick(p0: Long) {
                Log.d("SplashActivity", p0.toString())
            }
        }
        timer.start()
    }
}