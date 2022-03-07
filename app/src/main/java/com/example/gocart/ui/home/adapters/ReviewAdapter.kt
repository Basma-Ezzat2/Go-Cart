package com.example.gocart.ui.home.adapters

<<<<<<< Updated upstream
import android.content.Context
=======
>>>>>>> Stashed changes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.ui.home.pojo.UserData
import de.hdodenhof.circleimageview.CircleImageView

class ReviewAdapter(var userData: ArrayList<UserData>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

//    fun setReviewList(reviewList: ArrayList<UserData>) {
//        userData = reviewList
//        notifyDataSetChanged()
//    }

    inner class ReviewViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var image=itemView.findViewById<CircleImageView>(R.id.user_Image)
        var userName=itemView.findViewById<TextView>(R.id.user_Review)
        var review=itemView.findViewById<TextView>(R.id.reviewtxt)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewAdapter.ReviewViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.review_row,parent,false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewAdapter.ReviewViewHolder, position: Int) {
        var user=userData[position]
        holder.image.setImageResource(user.image)
        holder.review.text=user.review
        holder.userName.text=user.userName

    }

    override fun getItemCount(): Int =userData.size
}