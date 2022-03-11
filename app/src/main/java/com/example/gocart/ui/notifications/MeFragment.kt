package com.example.gocart.ui.notifications

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.auth.register_login.complete.SignInPasswordViewModel
import com.example.gocart.databinding.FragmentCompleteBinding
import com.example.gocart.databinding.FragmentMeBinding

class MeFragment : Fragment() {

    private lateinit var binding: FragmentMeBinding

    private val meViewModel by lazy {
        MeViewModel.create(this)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_me, container, false)


//
//        binding.goToSettingsBtn.setOnClickListener {
//            findNavController().navigate(R.id.action_navigation_notifications_to_settingsFragment)
//        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.hiText.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }

        binding.usernameTv.text = meViewModel.authenticationRepo.sharedPref.getSettings().customer!!.firstName


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.settings_Icon -> {
                findNavController().navigate(R.id.settingsFragment)
            }

            R.id.searchIcon -> {
                findNavController().navigate(R.id.searchFragment)
            }

            R.id.cartIcon -> {
                findNavController().navigate(R.id.cartFragment)
            }

        }
        return super.onOptionsItemSelected(item)

    }


}