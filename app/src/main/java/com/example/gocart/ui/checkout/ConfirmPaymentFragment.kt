package com.example.gocart.ui.checkout

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.databinding.ConfirmPaymentFragmentBinding
import com.example.gocart.ui.cart.CartAdapter
import kotlinx.coroutines.launch

class ConfirmPaymentFragment : Fragment() {

    companion object {
        fun newInstance() = ConfirmPaymentFragment()
    }

    lateinit var confirmPayment: Button

    private lateinit var viewModel: ConfirmPaymentViewModel

    var grandPrice = 0.0

    val binding by lazy {
        ConfirmPaymentFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ConfirmPaymentViewModel::class.java)
        viewModel.getAllData().observe(viewLifecycleOwner) {
            var totalPrice = 0.0
            for (i in it) {
                totalPrice += i.quantitiy * (i.variants?.get(0)?.price ?: 0.0)
            }
            binding.subTotalId.text = totalPrice.toString()

            grandPrice = totalPrice+10

            binding.grandTotalId.text = grandPrice.toString()

            binding.discountBtn.setOnClickListener {
                lifecycleScope.launch {
                    var discount = viewModel.getDiscount(binding.discountEt.text.toString())

                    if (discount == null){
                        Toast.makeText(context, "invalid code", Toast.LENGTH_SHORT).show()
                        binding.grandTotalId.text = grandPrice.toString()
                    }
                    else{
                        grandPrice += discount.value!!.toDouble()
                        binding.grandTotalId.text = grandPrice.toString()
                        binding.discountTv.text = discount.value!!.toString()
                    }
                }
            }


            confirmPayment = view.findViewById(R.id.confirmPaymentBtn)
            confirmPayment.setOnClickListener {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
            }
        }

    }
}