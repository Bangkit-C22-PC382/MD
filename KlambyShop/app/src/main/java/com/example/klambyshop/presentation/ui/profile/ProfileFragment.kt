package com.example.klambyshop.presentation.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.klambyshop.R
import com.example.klambyshop.data.repositories.UserPreference
import com.example.klambyshop.databinding.FragmentNotificationsBinding
import com.example.klambyshop.databinding.FragmentProfileBinding
import com.example.klambyshop.presentation.ui.NavigationViewModel
import com.example.klambyshop.presentation.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stateAppbar = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
            NavigationViewModel::class.java)

        stateAppbar.updateStateNavigation("Profile")
        onclick()
        val context = view.context

        binding.logout.setOnClickListener{
            val userPreference = UserPreference(context)
            userPreference.setUser(null)
            val intentToLogin = Intent(requireActivity(),LoginActivity::class.java)
            intentToLogin.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intentToLogin)
            activity?.finish()
        }
    }
    private fun onclick(){
        binding.myorder.setOnClickListener {
            toastShow()
        }
        binding.personadata.setOnClickListener {
            toastShow()
        }
        binding.addresbook.setOnClickListener {
            toastShow()
        }
        binding.more.setOnClickListener {
            toastShow()
        }
    }

    private fun toastShow(){
        val text = "Still under development!"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(requireActivity().applicationContext , text, duration)
        toast.show()


    }


}