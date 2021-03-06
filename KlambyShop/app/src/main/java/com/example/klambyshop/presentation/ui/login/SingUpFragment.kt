package com.example.klambyshop.presentation.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.klambyshop.R
import com.example.klambyshop.databinding.FragmentSingUpBinding


class SingUpFragment : Fragment() {

    private var _viewBinding: FragmentSingUpBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _viewBinding = FragmentSingUpBinding.inflate(inflater,container,false)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = LoginFragment()

        viewBinding.tvSingUp.setOnClickListener{
            val transaction = fragmentManager?.beginTransaction()
            transaction?.setCustomAnimations(
                R.anim.slide_in_invers,
                R.anim.slide_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
            transaction?.replace(R.id.fragment_container, fragment)
            transaction?.commit()

        }

        viewBinding.cirLoginButton.setOnClickListener{
            val transaction = fragmentManager?.beginTransaction()
            transaction?.setCustomAnimations(
                R.anim.slide_in_invers,
                R.anim.slide_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
            transaction?.replace(R.id.fragment_container, fragment)
            transaction?.commit()

        }
    }


}