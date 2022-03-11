package com.example.gocart.ui.category.subCategory

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.data.entity.categoriesPojo.Products
import com.example.gocart.databinding.FragmentSubMenBinding
import com.example.gocart.pojo.Product
import com.example.gocart.ui.category.CategoryViewModel
import com.example.gocart.ui.category.adapter.RecyclerViewAdapterProduct
import com.example.gocart.ui.home.activities.ProductDetailsActivity


class MenSubFragment : Fragment(),RecyclerViewAdapterProduct.OnItemClickListener {
    private lateinit var _binding: FragmentSubMenBinding
    private lateinit var viewModel: CategoryViewModel
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var recyclerViewAdapterProduct: RecyclerViewAdapterProduct


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         inflater.inflate(R.layout.fragment_sub_men, container, false)
        _binding = FragmentSubMenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[CategoryViewModel::class.java]
         toolbarConfig()
        return _binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        var flag = arguments?.get("Flag")
        var flags= arguments?.get("Flags")
        var subCollectionName:String =
        when(flag){
            0 ->  "ACCESSORIES"
            1 ->  "T-SHIRTS"
            2 ->  "SHOES"
            else ->  "SHOES"
        }
        recyclerViewAdapterProduct= RecyclerViewAdapterProduct(requireContext(),this)
        categoryViewModel = ViewModelProvider(requireActivity())[CategoryViewModel::class.java]
        categoryViewModel.liveDataResponse2.observe(viewLifecycleOwner,{
            var productList = it.products
            var list: MutableList<Product> = mutableListOf()
            for(i in productList){
                if((i.product_type.equals(subCollectionName))){
                    list.add(i)
                }
            }
            recyclerViewAdapterProduct.addList(list)
            _binding.menRecycler.adapter=recyclerViewAdapterProduct

        })
    }
    private fun toolbarConfig() {
        activity!!.findViewById<Toolbar>(R.id.toolbar).apply {
            var flag = arguments?.get("Flag")
            when(flag){
                0 -> title= "ACCESSORIES"
                1 -> title="T-SHIRTS"
                2 -> title="SHOES"
                else ->  0
            } as Unit
            setNavigationOnClickListener { findNavController().navigate(R.id.navigation_dashboard) }
            setNavigationIcon(R.drawable.ic_arrow_back)
        }
    }

    override fun onItemEditClickProduct(book: Product, position: Int) {
        val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
        intent.putExtra("product_id", book.id)
        intent.putExtra("cart_product",book )
        startActivity(intent)
    }
}