package com.example.gocart.ui.settings.address

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withCreated
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.auth.register_login.SignInViewModel
import com.example.gocart.auth.utils.Either
import com.example.gocart.auth.utils.LoginErrors
import com.example.gocart.databinding.FragmentAddressBinding
import com.example.gocart.pojo.Address
import com.example.gocart.ui.cart.CartFragment
import com.example.gocart.ui.cart.CartViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddressFragment : Fragment() {


    val binding by lazy {
        FragmentAddressBinding.inflate(layoutInflater)
    }

    val viewModel by lazy {
        AddressViewModel.create(this)
    }

    companion object {
        fun newInstance() = AddressFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        toolbarConfig()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        addressHandle()

        binding.addNewAddressBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addressFragment_to_addAddressFragment)
        }

    }

    fun addressHandle(){

        lifecycleScope.launch {
            val address = viewModel.getCustomerAddresses()
            when (address) {
                is Either.Error -> when (address.errorCode) {
                    LoginErrors.NoInternetConnection -> {
                        Toast.makeText(
                            requireContext(),
                            "NoInternetConnection" + address.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    LoginErrors.ServerError -> {

                        Toast.makeText(
                            requireContext(),
                            "ServerError" + address.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    LoginErrors.CustomerNotFound -> {
                        Toast.makeText(
                            requireContext(),
                            "CustomerNotFound" + address.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is Either.Success -> {
                    val addressAdapter = AddressAdapter(address.data,requireContext(),{
                        lifecycleScope.launch {
                            val address = viewModel.deleteAddress(it)
                            when (address) {
                                is Either.Error -> when (address.errorCode) {
                                    LoginErrors.NoInternetConnection -> {
                                        Toast.makeText(
                                            requireContext(),
                                            "NoInternetConnection" + address.message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    LoginErrors.ServerError -> {

                                        Toast.makeText(
                                            requireContext(),
                                            "ServerError" + address.message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    LoginErrors.CustomerNotFound -> {
                                        Toast.makeText(
                                            requireContext(),
                                            "CustomerNotFound" + address.message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                                is Either.Success -> {
                                    Toast.makeText(requireContext(), "Removed Successfully", Toast.LENGTH_SHORT).show()
                                    addressHandle()

                                }
                            }
                        }

                    })
                    withContext(Dispatchers.Main){
                        view?.findViewById<RecyclerView>(R.id.settingsAddressRV)?.apply {
                            adapter = addressAdapter
                            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                        }
                    }

                }
            }

        }

    }
    private fun toolbarConfig() {
        activity!!.findViewById<Toolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { findNavController().navigate(R.id.settingsFragment) }
            setNavigationIcon(R.drawable.ic_arrow_back)
        }
    }
}