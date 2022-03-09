package com.example.gocart.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentMeBinding

class MeFragment : Fragment() {

    private lateinit var meViewModel: MeViewModel
    private var _binding: FragmentMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        meViewModel =
            ViewModelProvider(this).get(MeViewModel::class.java)

        _binding = FragmentMeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.goToSettingsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_notifications_to_settingsFragment)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}