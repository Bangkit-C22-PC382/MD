package com.example.klambyshop.presentation

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.klambyshop.R
import com.example.klambyshop.databinding.ActivityNavigationBinding
import com.example.klambyshop.presentation.ui.NavigationViewModel

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        supportActionBar?.hide()

        val stateAppbar = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            NavigationViewModel::class.java)

        stateAppbar.state.observe(this){
            if(it != "Home"){
                binding.searchBar.visibility = View.GONE
                binding.titleAppbar.text = it
                binding.titleAppbar.visibility = View.VISIBLE
            }else{
                binding.searchBar.visibility = View.VISIBLE
                binding.titleAppbar.visibility = View.GONE

            }

        }

        onclick()


        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorite, R.id.navigation_notifications, R.id.navigation_cart, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun onclick(){
        binding.searchBar.setOnClickListener {
            toastShow()
        }
        binding.ivChat.setOnClickListener {
            toastShow()
        }
    }

    private fun toastShow(){
        val text = "Still under development!"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this.applicationContext , text, duration)
        toast.show()


    }
}