package com.example.klambyshop.presentation.ui.ads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.klambyshop.R
import com.example.klambyshop.databinding.ActivityAdsBinding

class AdsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val mFragmenManager = supportFragmentManager
        val mAdd1Fragment= Ads1Fragment()

        val fragment = mFragmenManager.findFragmentByTag(mAdd1Fragment::class.java.simpleName)
        if(fragment !is Ads1Fragment){
            mFragmenManager
                .beginTransaction()
                .replace(R.id.fragment_container,mAdd1Fragment, Ads1Fragment::class.java.simpleName)
                .commit()
        }

    }
}