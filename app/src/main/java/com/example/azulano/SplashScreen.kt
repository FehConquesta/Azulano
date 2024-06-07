package com.example.azulano

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Azulano)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)


        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val animationView = findViewById<LottieAnimationView>(R.id.animation_view)
        animationView.setAnimation(R.raw.bub)
        animationView.playAnimation()

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            },4000)

        }
    }
