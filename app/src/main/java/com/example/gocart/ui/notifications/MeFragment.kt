package com.example.gocart.ui.notifications

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


        if (meViewModel.authenticationRepo.sharedPref.getSettings().customer != null){
           // println(meViewModel.authenticationRepo.sharedPref.getSettings().customer)
               binding.whenUserLogged.visibility = View.VISIBLE
            binding.whenUserNotLogged.visibility = View.GONE
            binding.usernameTv.text = meViewModel.authenticationRepo.sharedPref.getSettings().customer!!.firstName
            meViewModel.getFourFromWishList().observe(viewLifecycleOwner, Observer {
                val ad = MeAdapter(it, meViewModel, requireContext())
                view!!.findViewById<RecyclerView>(R.id.wishRecyclerView).apply {
                    adapter = ad
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }
            })
        }else{
           // binding.usernameTv.text = getString(R.string.please_login)
            binding.whenUserLogged.visibility = View.GONE
            binding.whenUserNotLogged.visibility = View.VISIBLE

        }

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

        binding.moreWish.setOnClickListener{
            findNavController().navigate(R.id.wishListFragment)
        }

        //binding.usernameTv.text = meViewModel.authenticationRepo.sharedPref.getSettings().customer!!.firstName


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.me_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.setting -> {
                findNavController().navigate(R.id.settingsFragment)
            }

        }
        return super.onOptionsItemSelected(item)

    }


}