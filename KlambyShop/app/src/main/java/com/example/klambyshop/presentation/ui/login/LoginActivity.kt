package com.example.klambyshop.presentation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.klambyshop.R
import com.example.klambyshop.data.repositories.UserPreference
import com.example.klambyshop.databinding.ActivityLoginBinding
import com.example.klambyshop.presentation.NavigationActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val userPreference = UserPreference(this)
        val loginResult = userPreference.getUser()

        if(loginResult.token!=null){
            val intentToStory = Intent(this@LoginActivity,NavigationActivity::class.java)
            startActivity(intentToStory)
        }

        val mFragmenManager = supportFragmentManager
        val mFragmentLogin= LoginFragment()

        val fragment = mFragmenManager.findFragmentByTag(mFragmentLogin::class.java.simpleName)
        if(fragment !is LoginFragment){
            mFragmenManager
                .beginTransaction()
                .replace(R.id.fragment_container,mFragmentLogin, LoginFragment::class.java.simpleName)
                .commit()
        }

    }
}