package com.example.gocart.ui.dashboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gocart.R
import com.example.gocart.databinding.FragmentWomenBinding

class WomenFragment : Fragment() {
    private lateinit var _binding: FragmentWomenBinding
    private var clicked =false

    private val rotateOpen:Animation by lazy { AnimationUtils.loadAnimation(requireActivity(),R.anim.rotate_open_anim) }
    private val rotateClose:Animation by lazy { AnimationUtils.loadAnimation(requireActivity(),R.anim.rotate_close_anim) }
    private val fromBottom:Animation by lazy { AnimationUtils.loadAnimation(requireActivity(),R.anim.from_bottom_anim) }
    private val toBottom:Animation by lazy { AnimationUtils.loadAnimation(requireActivity(),R.anim.to_bottom_anim) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         inflater.inflate(R.layout.fragment_women, container, false)
        _binding = FragmentWomenBinding.inflate(inflater, container, false)

        return _binding.root

    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.floatingActionButtonAdd.setOnClickListener {
            onAddButtonClicked()
        }
        _binding.floatingActionButton.setOnClickListener {
            Toast.makeText(requireContext(),"Button 1 Clicked",Toast.LENGTH_SHORT).show()
        }
        _binding.floatingActionButton2.setOnClickListener {
            Toast.makeText(requireContext(),"Button 2 Clicked",Toast.LENGTH_SHORT).show()
        }
        _binding.floatingActionButton3.setOnClickListener {
            Toast.makeText(requireContext(),"Button 3 Clicked",Toast.LENGTH_SHORT).show()
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if(!clicked){
            _binding.floatingActionButton.startAnimation(fromBottom)
            _binding.floatingActionButton2.startAnimation(fromBottom)
            _binding.floatingActionButton3.startAnimation(fromBottom)
            _binding.floatingActionButtonAdd.startAnimation(rotateOpen)
        }else{
            _binding.floatingActionButton.startAnimation(toBottom)
            _binding.floatingActionButton2.startAnimation(toBottom)
            _binding.floatingActionButton3.startAnimation(toBottom)
            _binding.floatingActionButtonAdd.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if(!clicked){
            _binding.floatingActionButton.visibility=View.VISIBLE
            _binding.floatingActionButton2.visibility=View.VISIBLE
            _binding.floatingActionButton3.visibility=View.VISIBLE
        }else{
            _binding.floatingActionButton.visibility=View.INVISIBLE
            _binding.floatingActionButton2.visibility=View.INVISIBLE
            _binding.floatingActionButton3.visibility=View.INVISIBLE
        }
    }

}