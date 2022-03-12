package com.example.gocart.ui.home.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.ui.home.adapters.ReviewAdapter
import com.example.gocart.ui.home.pojo.UserData

class ReviewActivity : AppCompatActivity() {

    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        recyclerView = findViewById(R.id.reviewRecycler)
        toolbar = findViewById(R.id.toolbarReview)


        toolbar.setNavigationOnClickListener {
//            val intent = Intent(this, ProductDetailsActivity::class.java)
//            startActivity(intent)
            onBackPressed()
        }

        var myArrayList = ArrayList<UserData>()

//        val toolbar:Toolbar=binding.toolbarReview
//
//        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar.findViewById(R.id.toolbarReview))

        myArrayList.add(UserData("ayaabdelaziz355", R.drawable.girl1, "Like it ... Wonderfull ♡♡"))
        myArrayList.add(
            UserData(
                "hosammohammed401",
                R.drawable.boy,
                "ممكن أعرف أماكن الفروع والمنتجات تحفه ♡♡ "
            )
        )
        myArrayList.add(
            UserData(
                "fayzaelsayed587",
                R.drawable.girl2,
                "ما شاء الله مشوفتش منتجات زى دى ف مكان تانى بجد ربنا يحميكم ♡♡ "
            )
        )
        myArrayList.add(UserData("basma_ezzat01", R.drawable.girl3, " متاح توصيل ؟ ♡♡"))
        myArrayList.add((UserData("ayaalaa15", R.drawable.aa, "عندكم تليفون أرضي ... ؟")))
        myArrayList.add(UserData("enaselsayed47", R.drawable.ads, "خدمة العملاء سيئه جدا بجد "))
        myArrayList.add(UserData("seifmahmoud01010101010",R.drawable.boy,"عندكم نضارات شمس"))


        reviewAdapter = ReviewAdapter(myArrayList)
        recyclerView.adapter = reviewAdapter


    }
}