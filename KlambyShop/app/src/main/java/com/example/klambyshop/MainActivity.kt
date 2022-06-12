package com.example.klambyshop


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.klambyshop.data.repositories.AdsPreference
import com.example.klambyshop.data.repositories.UserPreference
import com.example.klambyshop.databinding.ActivityMainBinding
import com.example.klambyshop.presentation.ui.ads.AdsActivity
import com.example.klambyshop.presentation.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val adsPreference = AdsPreference(this)
        val adsResult = adsPreference.getUser()

        val timeDelay = 3000

        binding.logoTosca.animate()
            .alpha(1f)
            .setDuration(1500)
            .setListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator) {
                    binding.logoTosca.visibility = View.VISIBLE
                }
            })

        Handler(Looper.getMainLooper()).postDelayed({
            if(adsResult.ads != null){
                val intentToNavigationActivity = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intentToNavigationActivity)
                finish()

            }else{
                val intentToAdsActivity= Intent(this@MainActivity, AdsActivity::class.java)
                startActivity(intentToAdsActivity)
                finish()

            }

        },timeDelay.toLong())

    }
}