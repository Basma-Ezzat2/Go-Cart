package com.example.gocart.ui.home.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.fragment.app.Fragment
import com.example.gocart.R
import android.view.MenuInflater
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.databinding.FragmentHomeBinding
import com.example.gocart.ui.home.adapters.AdsAdapter
import com.example.gocart.ui.home.adapters.BrandsAdapter
import com.example.gocart.ui.home.pojo.brands.SmartCollections
import com.example.gocart.ui.home.viewmodels.HomeViewModel
import java.util.*


class HomeFragment : Fragment(), BrandsAdapter.BrandsClickListener, AdsAdapter.ImageClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var _binding: FragmentHomeBinding
    private lateinit var adsAdapter: AdsAdapter
    private lateinit var brandsAdapter: BrandsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity as AppCompatActivity?)!!.setSupportActionBar(_binding.toolbarHome)
        setHasOptionsMenu(true)
        brandsAdapter = BrandsAdapter(requireContext(), this)

        // multiSnapHelper.attachToRecyclerView(_binding.recyclerViewBrands)

        _binding.recyclerViewBrands.layoutManager = GridLayoutManager(
            context, 2,
            RecyclerView.HORIZONTAL, false
        )





        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        homeViewModel.liveDataResponse.observe(requireActivity(), {
            if(it.smartCollections.isEmpty()!=null){
                _binding.progressBar.visibility=View.GONE
            }
            val brandList = it.smartCollections
            // Log.d("ayya", "onViewCreated: "+brandList[0].title)
            brandsAdapter.addList(brandList)
            _binding.recyclerViewBrands.adapter = brandsAdapter
        })

        var list = mutableListOf<Int>()
        list.add(R.drawable.sale1)
        list.add(R.drawable.sale2)
        list.add(R.drawable.sale3)
        adsAdapter = AdsAdapter(requireContext(),this)
        adsAdapter.setContentList(list)


        val DELAY_MS: Long = 3000
        val PERIOD_MS: Long = 3000
        var currentPage = 0

        val handler = Handler(Looper.myLooper()!!)
        val Update = Runnable {
            if (currentPage == 4 - 1) {
                currentPage = 0
            }
            _binding.viewpager.setCurrentItem(currentPage++, true)
        }

        val timer = Timer()

        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)

        _binding.viewpager.adapter = adsAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
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

    override fun brandClick(collection: SmartCollections, position: Int) {
        homeViewModel.getProductByBrand(collection.title)
        //(activity as MainActivity).findViewById<Toolbar>(R.id.toolbar).title = collection.title
        val bundle = Bundle()
        bundle.putString("BrandName", collection.title)
        findNavController().navigate(R.id.productFragment,bundle)
    }

    override fun onImageClickListener(listt: List<Int>, position: Int) {

            val clipBoard : ClipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("EditText", "SUMMERSALE50OFF")

            clipBoard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), R.string.copied, Toast.LENGTH_SHORT).show()
        }


}