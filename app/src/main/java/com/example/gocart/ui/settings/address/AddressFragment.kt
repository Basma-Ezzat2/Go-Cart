package com.example.gocart.ui.settings.address

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.databinding.FragmentAddressBinding
import com.example.gocart.pojo.Address
import com.example.gocart.ui.cart.CartViewModel


class AddressFragment : Fragment() {

    lateinit var addressAdapter : AddressAdapter


    val binding by lazy {
        FragmentAddressBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var viewModel: AddressViewModel = ViewModelProvider(this).get(AddressViewModel::class.java)

      /*  viewModel.getAddressList().observe(viewLifecycleOwner, {
        val addressAdapter = AddressAdapter(it!!,requireContext())
        view.findViewById<RecyclerView>(R.id.settingsAddressRV).apply {
            adapter = addressAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }



    })*/

        binding.addNewAddressBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addressFragment_to_addAddressFragment)
        }



}
}