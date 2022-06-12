package com.example.klambyshop.presentation.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.klambyshop.databinding.FragmentNotificationsBinding
import com.example.klambyshop.presentation.ui.NavigationViewModel


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stateAppbar = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
            NavigationViewModel::class.java)

        stateAppbar.updateStateNavigation("Notifications")
        onclick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onclick(){
        binding.cartLock.setOnClickListener {
            toastShow()
        }
    }


    private fun toastShow(){
        val text = "Still under development!"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(activity?.applicationContext , text, duration)
        toast.show()


    }
}