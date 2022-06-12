package com.example.klambyshop.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.klambyshop.R
import com.example.klambyshop.data.model.LoginResult
import com.example.klambyshop.data.repositories.UserPreference
import com.example.klambyshop.databinding.FragmentLoginBinding
import com.example.klambyshop.presentation.NavigationActivity

class LoginFragment : Fragment() {

    private var _viewBinding: FragmentLoginBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _viewBinding = FragmentLoginBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = SingUpFragment()

        viewBinding.tvSingUp.setOnClickListener{
            val transaction = fragmentManager?.beginTransaction()
            transaction?.setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out_invers,
                R.anim.fade_in,
                R.anim.fade_out
            )
            transaction?.replace(R.id.fragment_container, fragment)
            transaction?.commit()
        }
        viewBinding.cirLoginButton.setOnClickListener {
            val context = view.context
            val userPreference = UserPreference(context)
            val login = LoginResult("1","beta","beta")
            userPreference.setUser(login)

            val intentToStory = Intent(requireActivity(),NavigationActivity::class.java)
            startActivity(intentToStory)
            requireActivity().finish()
        }

    }



}