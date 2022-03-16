package com.example.gocart.ui.notifications

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.databinding.FragmentCompleteBinding
import com.example.gocart.databinding.FragmentMeBinding
import com.example.gocart.ui.activities.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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


        if (meViewModel.authenticationRepo.sharedPref.isSignIn){
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


            meViewModel.getTwoFromOrderList().observe(viewLifecycleOwner, Observer {
                val ad = MeOrderAdapter(it, meViewModel, requireContext())
                view!!.findViewById<RecyclerView>(R.id.orderRecyclerView).apply {
                    adapter = ad
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
            })
        }else{
            binding.usernameTv.text = getString(R.string.please_login)
            binding.whenUserLogged.visibility = View.GONE
            binding.whenUserNotLogged.visibility = View.VISIBLE

        }


        binding.logOutBtn.setOnClickListener {
            showDialog()
        }

//
//        binding.goToSettingsBtn.setOnClickListener {
//            findNavController().navigate(R.id.action_navigation_notifications_to_settingsFragment)
//        }
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun showDialog() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Sign Out")
            .setMessage("Do you want to sign out?")
            .setPositiveButton("Ok") { _, _ ->
                requireActivity().deleteSharedPreferences("myPref")
                Toast.makeText(requireActivity(), "Successfull", Toast.LENGTH_SHORT)
                    .show()
                meViewModel.authenticationRepo.sharedPref.checkSignIn(false)
                findNavController().navigate(R.id.action_navigation_notifications_to_navigation_home)
            }
            .setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(requireActivity(), "Cancel", Toast.LENGTH_SHORT)
                    .show()
            }.show()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
       /* binding.hiText.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }*/

        binding.moreWish.setOnClickListener{
            findNavController().navigate(R.id.wishListFragment)
        }

        binding.meLoginbutton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_notifications_to_signInFragment)
        }

        binding.moreOrders.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_notifications_to_orderFragment)
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