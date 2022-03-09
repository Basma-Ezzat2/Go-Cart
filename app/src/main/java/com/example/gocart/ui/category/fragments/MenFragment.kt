package com.example.gocart.ui.category.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.data.entity.categoriesPojo.Products
import com.example.gocart.databinding.FragmentMenBinding
import com.example.gocart.ui.category.CategoryViewModel
import com.example.gocart.ui.category.adapter.RecyclerViewAdapterProduct
import com.example.gocart.ui.home.activities.ProductDetailsActivity


class MenFragment : Fragment(),RecyclerViewAdapterProduct.OnItemClickListener {
    private lateinit var _binding: FragmentMenBinding
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var recyclerViewAdapterProduct: RecyclerViewAdapterProduct
    private var clicked =false
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireActivity(),R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireActivity(),R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireActivity(),R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(requireActivity(),R.anim.to_bottom_anim) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_women, container, false)
        _binding = FragmentMenBinding.inflate(inflater, container, false)

        return _binding.root

    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.floatingActionButtonAdd.setOnClickListener {
            onAddButtonClicked()
        }
        _binding.floatingActionButton.setOnClickListener {
            val bundle = bundleOf("Flag" to 0)
            findNavController().navigate(R.id.menSubCategories,bundle)
        }
        _binding.floatingActionButton2.setOnClickListener {
            val bundle = bundleOf("Flag" to 1)
            findNavController().navigate(R.id.menSubCategories,bundle)
        }
        _binding.floatingActionButton3.setOnClickListener {
            val bundle = bundleOf("Flag" to 2)
            findNavController().navigate(R.id.menSubCategories,bundle)

        }

        recyclerViewAdapterProduct= RecyclerViewAdapterProduct(requireContext(),this)
        categoryViewModel = ViewModelProvider(requireActivity())[CategoryViewModel::class.java]

        categoryViewModel.liveDataResponse2.observe(viewLifecycleOwner,{
            val productList=it.products
            recyclerViewAdapterProduct.addList(productList)
            _binding.recyclerViewMyProduct3.adapter=recyclerViewAdapterProduct
        })

    }
    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if(!clicked){
            _binding.floatingActionButton.startAnimation(fromBottom)
            _binding.floatingActionButton2.startAnimation(fromBottom)
            _binding.floatingActionButton3.startAnimation(fromBottom)
            _binding.floatingActionButtonAdd.startAnimation(rotateOpen)
        }else{
            _binding.floatingActionButton.startAnimation(toBottom)
            _binding.floatingActionButton2.startAnimation(toBottom)
            _binding.floatingActionButton3.startAnimation(toBottom)
            _binding.floatingActionButtonAdd.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if(!clicked){
            _binding.floatingActionButton.visibility=View.VISIBLE
            _binding.floatingActionButton2.visibility=View.VISIBLE
            _binding.floatingActionButton3.visibility=View.VISIBLE
        }else{
            _binding.floatingActionButton.visibility=View.INVISIBLE
            _binding.floatingActionButton2.visibility=View.INVISIBLE
            _binding.floatingActionButton3.visibility=View.INVISIBLE
        }
    }

    override fun onItemEditClickProduct(book: Products, position: Int) {
        val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
        intent.putExtra("product_id", book.id)
        startActivity(intent)
    }


}