package com.example.gocart.ui.home

import android.os.Bundle
import android.view.*

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.example.gocart.R
import com.example.gocart.databinding.FragmentHomeBinding
import android.view.MenuInflater


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var _binding: FragmentHomeBinding
    private lateinit var adsAdapter: AdsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.setSupportActionBar(_binding.toolbar)
        var list= mutableListOf<Int>()
        list.add(R.drawable.w)
        list.add(R.drawable.y)
        list.add(R.drawable.z)
        adsAdapter= AdsAdapter(requireContext())
        adsAdapter.setContentList(list)
        _binding.viewpager.adapter=adsAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }


}