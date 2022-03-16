package com.example.gocart.ui.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.databinding.FragmentMeBinding
import com.example.gocart.databinding.FragmentOrderBinding
import com.example.gocart.ui.notifications.MeViewModel
import com.example.gocart.ui.wishList.WishListAdapter

class OrderFragment : Fragment() {

    companion object {
        fun newInstance() = OrderFragment()
    }
    private lateinit var binding: FragmentOrderBinding
    private lateinit var orderListAdapter: OrderAdapter


    private val orderViewModel by lazy {
        OrderViewModel.create(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)
        toolbarConfig()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (orderViewModel.authenticationRepo.sharedPref.isSignIn){

            binding.whenNotLogged.visibility = View.GONE
            binding.whenLogged.visibility = View.VISIBLE

            orderViewModel.getAllOrderList().observe(viewLifecycleOwner, Observer {
                orderListAdapter = OrderAdapter(it, orderViewModel, requireContext())
                view.findViewById<RecyclerView>(R.id.favRec).apply {
                    adapter = orderListAdapter
                    layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
                }
            })


        }else {
             binding.whenNotLogged.visibility = View.VISIBLE
             binding.whenLogged.visibility = View.GONE
        }
    }
    private fun toolbarConfig() {
        activity!!.findViewById<Toolbar>(R.id.toolbar).apply {
            title="Your Orders"
            setNavigationOnClickListener { findNavController().navigate(R.id.navigation_notifications) }
            setNavigationIcon(R.drawable.ic_arrow_back)
        }
    }

}