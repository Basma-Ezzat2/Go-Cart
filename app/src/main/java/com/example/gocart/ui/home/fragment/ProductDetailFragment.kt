package com.example.gocart.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.viewModelScope
import com.example.gocart.R
import com.example.gocart.pojo.Product
import com.example.gocart.ui.home.viewmodels.HomeViewModel
import kotlinx.coroutines.launch


class ProductDetailFragment : Fragment() {


    lateinit var addToCartBtn : Button
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}