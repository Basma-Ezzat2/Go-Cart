package com.example.gocart.ui.checkout

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.auth.utils.Either
import com.example.gocart.auth.utils.LoginErrors
import com.example.gocart.databinding.ChooseAddressPaymentFragmentBinding
import com.example.gocart.databinding.FragmentAddressBinding
import com.example.gocart.ui.settings.address.AddressAdapter
import com.example.gocart.ui.settings.address.AddressViewModel
import kotlinx.coroutines.launch

class ChooseAddressAndPaymentFragment : Fragment() {


    val binding by lazy {
        ChooseAddressPaymentFragmentBinding.inflate(layoutInflater)
    }

    val andPaymentViewModel by lazy {
        ChooseAddressAndPaymentViewModel.create(this)
    }


    companion object {
        fun newInstance() = ChooseAddressAndPaymentFragment()
    }

    lateinit var finishOrder : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolbarConfig()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        finishOrder = view.findViewById(R.id.finishOrderBtn)
        finishOrder.setOnClickListener {
            findNavController().navigate(R.id.action_chooseAddressAndPaymentFragment_to_confirmPaymentFragment)
        }


        lifecycleScope.launch {
            val address = andPaymentViewModel.getCustomerAddresses()
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
                    val chooseAddressAdapter = CheckoutAddressAdapter(address.data,requireContext())
                    view.findViewById<RecyclerView>(R.id.chooseAddressRV).apply {
                        adapter = chooseAddressAdapter
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


                    }
                }
            }

        }
    }
    private fun toolbarConfig() {
        activity!!.findViewById<Toolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { findNavController().navigate(R.id.cartFragment) }
            setNavigationIcon(R.drawable.ic_arrow_back)
        }
    }

}