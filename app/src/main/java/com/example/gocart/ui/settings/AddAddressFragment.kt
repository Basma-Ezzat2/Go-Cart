package com.example.gocart.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.gocart.R
import com.example.gocart.pojo.Address


class AddAddressFragment : Fragment() {

    var addressClass : Address = Address()
    lateinit var saveAddressBtn : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveAddressBtn = view?.findViewById<Button>(R.id.saveAddressBtn)!!

        saveAddressBtn.setOnClickListener {

            addressClass.country = view.findViewById<EditText>(R.id.addAddressCountry).text.toString()
            addressClass.city = view.findViewById<EditText>(R.id.addAddressCity).text.toString()
            addressClass.address = view.findViewById<EditText>(R.id.addAddressAddress).text.toString()
            addressClass.firstName = view.findViewById<EditText>(R.id.addAddressFullName).text.toString()
            addressClass.phone = view.findViewById<EditText>(R.id.addAddressPhone).text.toString()

            if (addressClass.country.isNullOrBlank() || addressClass.city.isNullOrBlank() || addressClass.address.isNullOrBlank() || addressClass.firstName.isNullOrBlank() || addressClass.phone.isNullOrBlank() ){
                Toast.makeText(context, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
            }
        }
    }


}