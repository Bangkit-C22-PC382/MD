package com.example.klambyshop.presentation.ui.ads

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.klambyshop.R
import com.example.klambyshop.data.model.AdsResult
import com.example.klambyshop.data.repositories.AdsPreference
import com.example.klambyshop.databinding.FragmentAdd2Binding
import com.example.klambyshop.presentation.ui.login.LoginActivity

class Ads2Fragment : Fragment() {

    private var _viewBinding: FragmentAdd2Binding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentAdd2Binding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = Ads3Fragment()

        viewBinding.ivNext.setOnClickListener{
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, fragment)
            transaction?.commit()
        }
        val fragmentback = Ads1Fragment()

        viewBinding.ivBack.setOnClickListener{
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, fragmentback)
            transaction?.commit()

        }
        viewBinding.tvSkip.setOnClickListener {
            val context = view.context
            val adsPreference = AdsPreference(context)
            val ads = AdsResult("true")
            adsPreference.setUser(ads)

            val intentToNavigationActivity = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intentToNavigationActivity)
            requireActivity().finish()

        }

    }

}