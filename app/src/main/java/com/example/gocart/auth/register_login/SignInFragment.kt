package com.example.gocart.auth.register_login

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var userEmail: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root


    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }


//        bindUi()
        val viewModel by lazy {
            SignInViewModel.create(this)
        }


        binding.loginBtn.setOnClickListener {
            if (validteForm()) {
                Log.d("email", "" + userEmail)
                viewModel.getData(userEmail)
                viewModel.loginSuccess.observe(viewLifecycleOwner) {
                    if (it!!) {
                        Toast.makeText(requireContext(), "You entered your mail ", Toast.LENGTH_LONG)
                            .show()
                      findNavController().navigate(R.id.signInPasswordFragment)
                    }
                }
            }
        }

    }

    private fun validteForm(): Boolean {
        userEmail = binding.emailEdt.text.toString()
        if (userEmail.isEmpty()) {
            binding.emailEdt.requestFocus()
            binding.emailEdt.error = "Required"
            return false
        }
        return true

    }

//    private fun bindUi() {
//        binding.tvSignUp.setOnClickListener {
//            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
//        }
//        binding.ivClose.setOnClickListener {
//            it.findNavController().popBackStack()
//        }
//    }
}