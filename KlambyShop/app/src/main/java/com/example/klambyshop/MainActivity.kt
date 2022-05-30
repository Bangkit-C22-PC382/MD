package com.example.klambyshop


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.klambyshop.databinding.ActivityMainBinding
import com.example.klambyshop.presentation.NavigationActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

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
            val intentToNavigationActivity = Intent(this@MainActivity, NavigationActivity::class.java)
            startActivity(intentToNavigationActivity)
            finish()
        },timeDelay.toLong())

    }
}