package com.example.gocart.ui.home.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentReviewBinding
import com.example.gocart.ui.home.adapters.ReviewAdapter
import com.example.gocart.ui.home.pojo.UserData
import androidx.appcompat.app.AppCompatActivity




class ReviewFragment : Fragment() {
    private lateinit var binding:FragmentReviewBinding
    private lateinit var reviewAdapter: ReviewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentReviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.toolbarReview.setNavigationOnClickListener {
//            findNavController().navigate(R.id.navigation_home)
//        }
        var myArrayList=ArrayList<UserData>()

//        val toolbar:Toolbar=binding.toolbarReview
//
//        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar.findViewById(R.id.toolbarReview))

        myArrayList.add(UserData("ayaabdelaziz355",R.drawable.girl1,"Like it ... Wonderfull"))
        myArrayList.add(UserData("hosammohammed401",R.drawable.boy,"ممكن أعرف أماكن الفروع منتجات تحفه ♡♡ "))
        myArrayList.add(UserData("fayzaelsayed587",R.drawable.girl2,"ما شاء الله مشوفتش منتجات زى دى ف مكان تانى بجد ربنا يحميكم "))
        myArrayList.add(UserData("basma_ezzat01",R.drawable.girl3,"متاح توصيل ؟"))

        reviewAdapter= ReviewAdapter(myArrayList)
        binding.reviewRecycler.adapter=reviewAdapter


    }


}