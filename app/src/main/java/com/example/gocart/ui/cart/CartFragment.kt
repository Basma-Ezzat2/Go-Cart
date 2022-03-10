package com.example.gocart.ui.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    lateinit var  checkoutBtn : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var viewModel: CartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        viewModel.getAllData().observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.totalPriceText).apply {
                var totalPrice = 0.0
                for (i in it){
                    totalPrice += i.quantitiy * (i.variants?.get(0)?.price?:0.0)
                }
                text = totalPrice.toString()
            }
            val cartAdapter = CartAdapter(it, { productCartModule ->
                if (productCartModule.quantitiy <= 1) {
                    lifecycleScope.launch {
                        //add confirmation dialog
                        viewModel.deleteCartItem(productCartModule)
                    }
                } else {
                    lifecycleScope.launch {
                        viewModel.updateCard(productCartModule.copy(quantitiy = productCartModule.quantitiy - 1))
                    }

                }

            }, { productCartModule ->
                lifecycleScope.launch {
                    viewModel.updateCard(productCartModule.copy(quantitiy = productCartModule.quantitiy + 1))
                }
            }, { productCartModule ->
                lifecycleScope.launch {
                    viewModel.deleteCartItem(productCartModule.copy(quantitiy = productCartModule.quantitiy + 1))
                }
            }
            )

            view.findViewById<RecyclerView>(R.id.rv_cartItems).apply {
                adapter = cartAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }


        checkoutBtn = view.findViewById<Button>(R.id.checkoutButton)
        checkoutBtn.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_chooseAddressAndPaymentFragment)
        }


    }



}