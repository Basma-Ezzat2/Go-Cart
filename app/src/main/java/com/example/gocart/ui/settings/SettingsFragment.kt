package com.example.gocart.ui.settings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentMeBinding
import com.example.gocart.databinding.SettingsFragmentBinding
import com.example.gocart.utils.Constants.isUSD
import com.google.android.material.bottomsheet.BottomSheetDialog

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    lateinit var bottomSheetDialog : BottomSheetDialog

    private lateinit var viewModel: SettingsViewModel
    val binding by lazy {
        SettingsFragmentBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolbarConfig()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.settingsAddressCvId.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_addressFragment)
        }

        binding.settingsCurrencyCvId.setOnClickListener {
            bottomSheetDialog = BottomSheetDialog(context!!,R.style.BottomSheetTheme)
            var sheetView : View = LayoutInflater.from(context).inflate(R.layout.fragment_bottom_sheet, view.findViewById(R.id.bottom_sheet))
            sheetView.findViewById<Button>(R.id.currencyEgpBtn).setOnClickListener {
                Toast.makeText(context, "chosen EGP", Toast.LENGTH_SHORT).show()
                isUSD = false
                bottomSheetDialog.dismiss()
            }

            sheetView.findViewById<Button>(R.id.currencyUsdBtn).setOnClickListener {
                Toast.makeText(context, "chosen USD", Toast.LENGTH_SHORT).show()
                isUSD = true
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.setContentView(sheetView)
            bottomSheetDialog.show()

        }


    }
    private fun toolbarConfig() {
        activity!!.findViewById<Toolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { findNavController().navigate(R.id.navigation_notifications) }
            setNavigationIcon(R.drawable.ic_arrow_back)
        }
    }

}