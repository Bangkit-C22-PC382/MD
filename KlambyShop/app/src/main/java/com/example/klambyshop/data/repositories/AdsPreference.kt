package com.example.klambyshop.data.repositories

import android.content.Context
import com.example.klambyshop.data.model.AdsResult
import com.example.klambyshop.data.model.LoginResult

class AdsPreference(context: Context){
    companion object{
        private const val PREFS_NAME = "ads_pref"
        private const val ADS = "ads"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    fun setUser(value: AdsResult?) {
        val editor = preferences.edit()
        editor.putString(ADS, value?.ads)
        editor.apply()
    }

    fun getUser(): AdsResult {
        val model = AdsResult()
        model.ads = preferences.getString(ADS,null)
        return model
    }



}