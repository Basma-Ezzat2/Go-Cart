package com.example.gocart.ui.wishList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.databinding.FragmentWishListBinding
import com.example.gocart.ui.home.pojo.productdetail.Product
import com.example.gocart.ui.order.OrderViewModel

class WishListFragment : Fragment() {

    private lateinit var bindingAllWishListFragment: FragmentWishListBinding
    private lateinit var withListAdapter: WishListAdapter


    private val wishlistViewModel by lazy {
        WishListViewModel.create(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingAllWishListFragment = FragmentWishListBinding.inflate(inflater, container, false)
        toolbarConfig()
        return bindingAllWishListFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //var viewModel: WishListViewModel = ViewModelProvider(this).get(WishListViewModel::class.java)

        if (wishlistViewModel.authenticationRepo.sharedPref.isSignIn){

            bindingAllWishListFragment.whenNotLogged.visibility = View.GONE
            bindingAllWishListFragment.whenLogged.visibility = View.VISIBLE
            wishlistViewModel.getAllWishList().observe(viewLifecycleOwner, Observer {
                withListAdapter = WishListAdapter(it, wishlistViewModel, requireContext())
                view.findViewById<RecyclerView>(R.id.favRec).apply {
                    adapter = withListAdapter
                    layoutManager = GridLayoutManager(context, 2,LinearLayoutManager.VERTICAL, false)
                }
            })


        }else{
            bindingAllWishListFragment.whenNotLogged.visibility = View.VISIBLE
            bindingAllWishListFragment.whenLogged.visibility = View.GONE
        }


    }
    private fun toolbarConfig() {
        activity!!.findViewById<Toolbar>(R.id.toolbar).apply {
            title="My WishList"
            setNavigationOnClickListener { findNavController().navigate(R.id.navigation_home) }
            setNavigationIcon(R.drawable.ic_arrow_back)
        }
    }

}