package com.example.gocart.ui.settings.address

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.auth.utils.Either
import com.example.gocart.auth.utils.LoginErrors
import com.example.gocart.pojo.Address
import kotlinx.coroutines.launch


class AddAddressFragment : Fragment() {

    var addressClass : Address = Address()
    lateinit var saveAddressBtn : Button


    val viewModel by lazy {
        AddressViewModel.create(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        saveAddressBtn = view?.findViewById<Button>(R.id.saveAddressBtn)!!

        saveAddressBtn.setOnClickListener {



            addressClass.country = view.findViewById<EditText>(R.id.addAddressCountry).text.toString()
            addressClass.city = view.findViewById<EditText>(R.id.addAddressCity).text.toString()
            addressClass.address = view.findViewById<EditText>(R.id.addAddressAddress).text.toString()
            addressClass.firstName = view.findViewById<EditText>(R.id.addAddressFullName).text.toString()
            addressClass.phone = view.findViewById<EditText>(R.id.addAddressPhone).text.toString()

            if (addressClass.country.isNullOrBlank() || addressClass.city.isNullOrBlank() || addressClass.address.isNullOrBlank() || addressClass.firstName.isNullOrBlank() || addressClass.phone.isNullOrBlank() ){
                Toast.makeText(context, "Please fill all required fields", Toast.LENGTH_LONG).show()
            }
            else{
                lifecycleScope.launch {
                    val address = viewModel.addAddress(addressClass)
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
                            Log.d("singin", "" + address.data)
                            Toast.makeText(requireContext(), "Added Successfully", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_addAddressFragment_to_addressFragment)

                        }
                    }

                }

            }
        }
    }


}