package com.example.gocart.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.databinding.FragmentFavouriteBinding


class FavouriteFragment : Fragment() {

    private lateinit var binding:FragmentFavouriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentFavouriteBinding.inflate(inflater)
        return binding.root

//        binding.recyclerViewFav.layoutManager = GridLayoutManager(context, 2,RecyclerView.VERTICAL,false)
//
////        GridLayoutManager(
////            requireContext(),
////            4,
////            RecyclerView.VERTICAL,
////            false
////        ).apply {
////            // specify the layout manager for recycler view
////            binding.recyclerView.layoutManager = this
////        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
//            android.R.id.home->
        }
        return super.onOptionsItemSelected(item)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigate(R.id.navigation_home)

        }
        binding.recyclerViewFav.layoutManager = GridLayoutManager(context, 2,RecyclerView.VERTICAL,false)


    }


}