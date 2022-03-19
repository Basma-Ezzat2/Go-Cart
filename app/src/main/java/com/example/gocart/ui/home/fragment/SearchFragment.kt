package com.example.gocart.ui.home.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.SearchFragmentBinding
import com.example.gocart.ui.home.activities.ProductDetailsActivity
import com.example.gocart.ui.home.adapters.SearchAdapter
import com.example.gocart.ui.home.viewmodels.HomeViewModel
import androidx.core.content.ContextCompat.getSystemService


class SearchFragment : Fragment(), SearchAdapter.ProductsClickListener {

    private lateinit var binding: SearchFragmentBinding
    private lateinit var etSearch: EditText
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        searchAdapter = SearchAdapter(requireContext(), this)
        activity!!.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.nav_view)
            .apply {
                visibility = View.GONE
            }

        homeViewModel.searchedProduct.observe(requireActivity(), {
            if (it.isEmpty()) {
                searchAdapter.clearRecycler()
            } else {
                searchAdapter.clearRecycler()
                searchAdapter.addList(it)
                binding.searchRecycler.adapter = searchAdapter

            }


        })


      /*  val spinnerList = ArrayList<String>()
        spinnerList.add(R.string.show_all.toString())
        spinnerList.add(R.string.show_by_type.toString())*/
        /*val Adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.spinner.adapter = Adapter*/

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 1) {
//                    searchAdapter.clearRecycler()
                    homeViewModel.sortSearch(1)
                } else {
//                    searchAdapter.clearRecycler()
//                    homeViewModel.searchedProduct.observe(requireActivity(), {
//                        if (it.isEmpty()) {
//                            searchAdapter.clearRecycler()
//                        } else {
//                            searchAdapter.addList(it)
//                            binding.searchRecycler.adapter = searchAdapter
//
//                        }
//
//
//                    })
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        activity!!.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                findNavController().navigate(R.id.navigation_home)
            }
        }




        etSearch = activity!!.findViewById(R.id.et_search)
        etSearch.visibility = View.VISIBLE

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                homeViewModel.searchAll(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }

    override fun onStop() {
        super.onStop()
        etSearch.visibility = View.GONE
        etSearch.text.clear()
        activity!!.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.nav_view)
            .apply {
                visibility = View.VISIBLE
            }
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(view!!.windowToken, 0)

    }


    override fun onResume() {
        super.onResume()
        etSearch.requestFocus()
        etSearch.isFocusableInTouchMode = true
        etSearch.setSelection(etSearch.text.length)
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

    }


    override fun onProductSearchClickListener(
        collection: com.example.gocart.ui.home.pojo.search.Products,
        position: Int
    ) {
        val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
        intent.putExtra("product_id", collection.id)
        startActivity(intent)
    }
}