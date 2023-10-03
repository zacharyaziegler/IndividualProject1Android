package com.example.individualproj1android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val iv_check = findViewById<ImageView>(R.id.iv_check) // find image view of check for logo

        iv_check.animate().setDuration(1500).alpha(1f).withEndAction { // animation for splash screen
            val i = Intent(this, MainActivity::class.java) // create intent of main activity to launch after animation
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}