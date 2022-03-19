package com.example.gocart.ui.category

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.databinding.FragmentCategoryBinding
import com.example.gocart.ui.category.adapter.RecyclerViewAdapterProduct
import com.example.gocart.ui.category.fragments.KidsFragment
import com.example.gocart.ui.category.fragments.MenFragment
import com.example.gocart.ui.category.fragments.SalesFragment
import com.example.gocart.ui.category.fragments.WomenFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class CategoryFragment : Fragment() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var _binding: FragmentCategoryBinding
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
//        (activity as AppCompatActivity?)!!.setSupportActionBar(_binding.toolbar)
//        _binding.toolbar.title = "Category"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.fav_Icon -> {
                findNavController().navigate(R.id.wishListFragment)
            }
            R.id.searchIcon -> {
                findNavController().navigate(R.id.searchFragment)
            }

            R.id.cartIcon -> {
                findNavController().navigate(R.id.cartFragment)
            }
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
                .add(R.string.men, MenFragment::class.java)
                .add(R.string.women, WomenFragment::class.java)
                .add(R.string.kids, KidsFragment::class.java)
                .add(R.string.sales, SalesFragment::class.java)
                .create()
        )
        _binding.viewpager2.adapter = adapter
        _binding.smartTabs.setViewPager(_binding.viewpager2)
    }
}