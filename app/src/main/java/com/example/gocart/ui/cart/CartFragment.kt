package com.example.gocart.ui.cart

import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.databinding.FragmentAddressBinding
import com.example.gocart.databinding.FragmentCartBinding
import com.example.gocart.ui.network.Connectivity
import com.example.gocart.ui.notifications.MeViewModel
import com.example.gocart.utils.Constants.convertPrice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    lateinit var  checkoutBtn : Button
    lateinit var connectivity : Connectivity


    val binding by lazy {
        FragmentCartBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        CartViewModel.create(this)
    }

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


        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_signInFragment)
        }


        connectivity = Connectivity(activity!!.application)

        connectivity.observe(this, { isConnected ->

            if (isConnected){
                binding.whenNoInternet.visibility = View.GONE
                if (viewModel.authenticationRepo.sharedPref.isSignIn){

                    viewModel.getAllData().observe(viewLifecycleOwner, {
                        if (it.isEmpty()) {
                            binding.whenThereIsItemsGroup.visibility = View.GONE
                            binding.whenThereIsNoItemsGroup.visibility = View.VISIBLE
                        } else {
                            binding.whenThereIsItemsGroup.visibility = View.VISIBLE
                            binding.whenThereIsNoItemsGroup.visibility = View.GONE
                            view.findViewById<TextView>(R.id.totalPriceText).apply {
                                var totalPrice = 0.0
                                for (i in it) {
                                    totalPrice += i.quantitiy * (i.variants?.get(0)?.price ?: 0.0)
                                }
                                CoroutineScope(Dispatchers.Main).launch {
                                    text = convertPrice(totalPrice)
                                }
                            }
                            val cartAdapter = CartAdapter(it, { productCartModule ->
                                if (productCartModule.quantitiy <= 1) {
                                    //add confirmation dialog
                                    var builder = AlertDialog.Builder(requireContext())
                                    builder.setMessage("Are you sure you want to delete this item ?")
                                    builder.setPositiveButton("Yes") { dialogInterface, i ->
                                        lifecycleScope.launch {
                                            viewModel.deleteCartItem(productCartModule)
                                        }
                                    }

                                    builder.setNegativeButton("Cancel") { dialogInterface, i ->
                                        Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show()
                                    }

                                    val alertDialog: AlertDialog = builder.create()
                                    alertDialog.setCancelable(false)
                                    alertDialog.show()
                                    alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                                        .setBackgroundColor(
                                            Color.BLUE
                                        )
                                    alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                                        .setTextColor(
                                            Color.WHITE
                                        )
                                    alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE)
                                        .setTextColor(
                                            Color.DKGRAY
                                        )


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

                                var builder = AlertDialog.Builder(requireContext())
                                builder.setMessage("Are you sure you want to delete this item ?")
                                builder.setPositiveButton("Yes") { dialogInterface, i ->
                                    lifecycleScope.launch {
                                        viewModel.deleteCartItem(productCartModule.copy(quantitiy = productCartModule.quantitiy - 1))
                                    }
                                }

                                builder.setNegativeButton("Cancel") { dialogInterface, i ->
                                    Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show()
                                }

                                val alertDialog: AlertDialog = builder.create()
                                alertDialog.setCancelable(false)
                                alertDialog.show()
                                alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                                    .setBackgroundColor(
                                        Color.BLUE
                                    )
                                alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                                    .setTextColor(
                                        Color.WHITE
                                    )
                                alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE)
                                    .setTextColor(
                                        Color.DKGRAY
                                    )

                            }
                            )

                            view.findViewById<RecyclerView>(R.id.rv_cartItems).apply {
                                adapter = cartAdapter
                                layoutManager =
                                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            }
                        }

                    })


                }else {

                    binding.whenNotLoggedIn.visibility = View.VISIBLE
                    binding.whenThereIsItemsGroup.visibility = View.GONE
                    binding.whenThereIsNoItemsGroup.visibility = View.GONE

                }

            }else{
                binding.whenNoInternet.visibility = View.VISIBLE
                binding.whenNotLoggedIn.visibility = View.GONE
                binding.whenThereIsItemsGroup.visibility = View.GONE
                binding.whenThereIsNoItemsGroup.visibility = View.GONE
            }

        })






        checkoutBtn = view.findViewById<Button>(R.id.checkoutButton)
        checkoutBtn.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_chooseAddressAndPaymentFragment)
        }


    }

    private fun toolbarConfig() {
        activity!!.findViewById<Toolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { findNavController().navigate(R.id.navigation_home) }
            setNavigationIcon(R.drawable.ic_arrow_back)
        }
    }

}