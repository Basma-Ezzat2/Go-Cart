package com.example.gocart.ui.home.fragment

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.SearchFragmentBinding
import com.example.gocart.ui.home.adapters.ProductAdapter
import com.example.gocart.ui.home.adapters.SearchAdapter
import com.example.gocart.ui.home.pojo.product.Products
import com.example.gocart.ui.home.viewmodels.HomeViewModel

class SearchFragment : Fragment(),SearchAdapter.ProductsClickListener {

    private lateinit var binding: SearchFragmentBinding
    private lateinit var etSearch: EditText
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var searchAdapter:SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel=ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        searchAdapter = SearchAdapter(requireContext(), this)

        homeViewModel.productsSearch.observe(requireActivity(),{

            searchAdapter.addList(it.products)
            binding.searchRecycler.adapter=searchAdapter

        })
        activity!!.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                findNavController().navigate(R.id.navigation_home)
            }
        }

        etSearch = activity!!.findViewById(R.id.et_search)
        etSearch?.visibility = View.VISIBLE

    }

    override fun onStop() {
        super.onStop()
        etSearch.visibility = View.GONE
//        Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()
//        if (etSearch.visibility == View.VISIBLE){
//            Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()

    }

    override fun onProductSearchClickListener(
        collection: com.example.gocart.ui.home.pojo.search.Products,
        position: Int
    ) {
        TODO("Not yet implemented")
    }


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.toolbar_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.searchIcon -> {
//            }
//        }
//        return super.onOptionsItemSelected(item)
//
//    }


}