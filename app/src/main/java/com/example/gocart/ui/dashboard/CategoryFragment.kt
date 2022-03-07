package com.example.gocart.ui.dashboard

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.databinding.FragmentCategoryBinding
import com.example.gocart.ui.dashboard.fragments.KidsFragment
import com.example.gocart.ui.dashboard.fragments.MenFragment
import com.example.gocart.ui.dashboard.fragments.SalesFragment
import com.example.gocart.ui.dashboard.fragments.WomenFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class CategoryFragment : Fragment() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var _binding: FragmentCategoryBinding
    private lateinit var recyclerViewAdapterProduct:RecyclerViewAdapterProduct

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        setUpTabs()
        return _binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        categoryViewModel.liveDataResponse.observe(requireActivity(),{
            val productList=it.products
            recyclerViewAdapterProduct.addList(productList)
            val recycler:RecyclerView=view.findViewById(R.id.recyclerViewMyProduct)
            recycler.adapter=recyclerViewAdapterProduct


        })
//        (activity as AppCompatActivity?)!!.setSupportActionBar(_binding.toolbar)
//        _binding.toolbar.title = "Category"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()

        if (item.itemId == R.id.cartIcon){
            navController.navigate(R.id.action_navigation_dashboard_to_cartFragment)
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }


    private fun setUpTabs(){
        val adapter = FragmentPagerItemAdapter(
            childFragmentManager,
            FragmentPagerItems.with(activity)
                .add("Men", MenFragment::class.java)
                .add("Women", WomenFragment::class.java)
                .add("Kids", KidsFragment::class.java)
                .add("Sales", SalesFragment::class.java)
                .create()
        )
        _binding.viewpager2.adapter = adapter
        _binding.tabs.setupWithViewPager(_binding.viewpager2)
    }
}