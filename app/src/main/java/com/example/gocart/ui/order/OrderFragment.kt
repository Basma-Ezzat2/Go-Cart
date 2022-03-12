package com.example.gocart.ui.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderViewModel.getAllOrderList().observe(viewLifecycleOwner, Observer {
            orderListAdapter = OrderAdapter(it, orderViewModel, requireContext())
            view.findViewById<RecyclerView>(R.id.favRec).apply {
                adapter = orderListAdapter
                layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
            }
        })
    }

}