package com.example.gocart.ui.checkout

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.gocart.R

class ChooseAddressAndPaymentFragment : Fragment() {

    companion object {
        fun newInstance() = ChooseAddressAndPaymentFragment()
    }

    private lateinit var andPaymentViewModel: ChooseAddressAndPaymentViewModel
    lateinit var finishOrder : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choose_address_payment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        andPaymentViewModel = ViewModelProvider(this).get(ChooseAddressAndPaymentViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        finishOrder = view.findViewById(R.id.finishOrderBtn)
        finishOrder.setOnClickListener {
            findNavController().navigate(R.id.action_chooseAddressAndPaymentFragment_to_confirmPaymentFragment)
        }
    }

}