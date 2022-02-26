package com.example.gocart.ui.dashboard

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.R
import com.example.gocart.databinding.FragmentCategoryBinding
import com.example.gocart.ui.dashboard.adapter.ViewPagerAdapter
import com.example.gocart.ui.dashboard.fragments.KidsFragment
import com.example.gocart.ui.dashboard.fragments.MenFragment
import com.example.gocart.ui.dashboard.fragments.WomenFragment
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
        categoryViewModel =
            ViewModelProvider(this)[CategoryViewModel::class.java]
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        setUpTabs()
        return _binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.setSupportActionBar(_binding.toolbar)
        _binding.toolbar.title = "Category"
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
                .create()
        )
        _binding.viewpager2.adapter = adapter
        _binding.tabs.setViewPager(_binding.viewpager2)
    }
}