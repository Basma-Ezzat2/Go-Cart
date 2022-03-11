package com.example.gocart.auth.register_login.complete

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentCompleteBinding


class SignInPasswordFragment : Fragment() {

    private lateinit var binding: FragmentCompleteBinding
    private lateinit var userPass: String

    private val viewModel by lazy {
        SignInPasswordViewModel.create(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_complete, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginBtn.setOnClickListener {
            userPass = binding.passwordEdt.text.toString()
            Log.d("fayza", "" + viewModel.authenticationRepo.sharedPref.getSettings().customer!!.lastName)

            if (viewModel.authenticationRepo.sharedPref.getSettings().customer!!.lastName.equals(userPass)
            ) {
                Toast.makeText(requireContext(), "Logged in successfully", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_signInPasswordFragment_to_navigation_notifications)
            }
        }
       viewModel.authenticationRepo.sharedPref.getSettings()
    }
}