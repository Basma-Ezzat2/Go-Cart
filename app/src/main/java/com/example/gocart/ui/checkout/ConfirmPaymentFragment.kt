package com.example.gocart.ui.checkout

import android.app.Dialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.databinding.ConfirmPaymentFragmentBinding
import com.example.gocart.pojo.Address
import com.example.gocart.pojo.OrderObject
import com.example.gocart.ui.activities.MainActivity
import com.example.gocart.ui.cart.CartAdapter
import com.example.gocart.ui.cart.CartViewModel
import com.example.gocart.ui.settings.address.AddressViewModel
import com.example.gocart.utils.Constants.convertPrice
import com.google.android.gms.wallet.AutoResolveHelper
import com.google.android.gms.wallet.PaymentData
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class ConfirmPaymentFragment : Fragment() {

    companion object {
        fun newInstance() = ConfirmPaymentFragment()
    }

    lateinit var dialog : Dialog
    lateinit var btnOk : Button

    private val loadPaymentDataRequestCode = 991

    private lateinit var googlePayButton: View

    var grandPrice = 0.0

    val binding by lazy {
        ConfirmPaymentFragmentBinding.inflate(layoutInflater)
    }

    val viewModel by lazy {
        ConfirmPaymentViewModel.create(this)
    }

    val cartViewModel by lazy {
        CartViewModel.create(this)
    }

    val address:Address? by lazy {
        arguments?.get("address") as Address?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.e("address", "onCreateView: "+address, )

        googlePayButton = binding.googlePayButton.root
        googlePayButton.setOnClickListener { requestPayment() }

        dialog = Dialog(context!!)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawable(context!!.resources.getDrawable(R.drawable.custom_dialog_bg))
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(true)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation

        btnOk = dialog.findViewById(R.id.btn_okay)
        btnOk.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(R.id.action_confirmPaymentFragment_to_navigation_home)
        }





        binding.cashOnDelBtn.setOnClickListener {
            //Toast.makeText(context, "Payment Successfull", Toast.LENGTH_LONG).show()
            var order = OrderObject( title = Date().toString(), price = binding.grandTotalPaymentiD.text.toString().toDouble(),address = address )
            lifecycleScope.launch {
                viewModel.addOrder(order)
                viewModel.createOrderAp(order)
                cartViewModel.deleteAllCart()
            }
            dialog.show()
        }

        // Check whether Google Pay can be used to complete a payment
        viewModel.canUseGooglePay.observe(this, Observer(::setGooglePayAvailable))
        toolbarConfig()
        return binding.root
    }

    private fun setGooglePayAvailable(available: Boolean) {
        if (available) {
            googlePayButton.visibility = View.VISIBLE
        } else {
            Toast.makeText(
                context,
                "Unfortunately, Google Pay is not available on this device",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun requestPayment() {

        // Disables the button to prevent multiple clicks.
        googlePayButton.isClickable = false

        // The price provided to the API should include taxes and shipping.
        // This price is not displayed to the user.

        val dummyPriceCents = (binding.grandTotalPaymentiD.text.toString().toDouble()*100).toLong()
        val task = viewModel.getLoadPaymentDataTask(dummyPriceCents)

        // Shows the payment sheet and forwards the result to the onActivityResult method.
        AutoResolveHelper.resolveTask(task, requireActivity(), loadPaymentDataRequestCode)
    }

    // Suppressing deprecation until `registerForActivityResult` is available on the Google Pay API.
     fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("HOSSAM","messageeee")
        when (requestCode) {
            // Value passed in AutoResolveHelper
            loadPaymentDataRequestCode -> {
                when (resultCode) {
                    AppCompatActivity.RESULT_OK ->
                        data?.let { intent ->
                            PaymentData.getFromIntent(intent)?.let(::handlePaymentSuccess)
                        }

                    AppCompatActivity.RESULT_CANCELED -> {
                        // The user cancelled the payment attempt
                    }

                    AutoResolveHelper.RESULT_ERROR -> {
                        AutoResolveHelper.getStatusFromIntent(data)?.let {
                            handleError(it.statusCode)
                        }
                    }
                }

                // Re-enables the Google Pay payment button.
                googlePayButton.isClickable = true
            }
        }
    }



    private fun handlePaymentSuccess(paymentData: PaymentData) {
        val paymentInformation = paymentData.toJson() ?: return

        try {
            // Token will be null if PaymentDataRequest was not constructed using fromJson(String).
            val paymentMethodData = JSONObject(paymentInformation).getJSONObject("paymentMethodData")
            val billingName = paymentMethodData.getJSONObject("info")
                .getJSONObject("billingAddress").getString("name")
            Log.d("BillingName", billingName)

            //Toast.makeText(context, getString(R.string.payments_show_name, billingName), Toast.LENGTH_LONG).show()
            var order = OrderObject( title = Date().toString(), price = binding.grandTotalPaymentiD.text.toString().toDouble() , address = address)
            lifecycleScope.launch {
                viewModel.addOrder(order)
                viewModel.createOrderAp(order)
                cartViewModel.deleteAllCart()
            }
            dialog.show()

            // Logging token string.
            Log.d(
                "GooglePaymentToken", paymentMethodData
                    .getJSONObject("tokenizationData")
                    .getString("token")
            )

        } catch (error: JSONException) {
            Log.e("handlePaymentSuccess", "Error: $error")
        }
    }

    private fun handleError(statusCode: Int) {
        Log.w("loadPaymentData failed", "Error code: $statusCode")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).liveData.apply {
            observe(viewLifecycleOwner){
                if (it != null)
                    activityResult(it.requestCode,it.resultCode,it.data)

                this.postValue(null)
            }
        }



        googlePayButton = binding.googlePayButton.root
        googlePayButton.setOnClickListener { requestPayment() }

        viewModel.canUseGooglePay.observe(this, Observer(::setGooglePayAvailable))

        viewModel.getAllData().observe(viewLifecycleOwner) {
            var totalPrice = 0.0
            for (i in it) {
                totalPrice += i.quantitiy * (i.variants?.get(0)?.price ?: 0.0)
            }
            lifecycleScope.launch {
                binding.subTotalId.text = convertPrice(totalPrice)
            }

            grandPrice = totalPrice+10

            lifecycleScope.launch {
                binding.grandTotalId.text = convertPrice(grandPrice)
                binding.grandTotalPaymentiD.text = grandPrice.toString()
                binding.shippingFee.text = convertPrice(10.0)
            }


            binding.discountBtn.setOnClickListener {
                lifecycleScope.launch {
                    var discount = viewModel.getDiscount(binding.discountEt.text.toString())

                    if (discount == null){
                        Toast.makeText(context, "invalid code", Toast.LENGTH_SHORT).show()
                        binding.grandTotalId.text = convertPrice(grandPrice)
                    }
                    else{
                        grandPrice += discount.value!!.toDouble()
                        binding.grandTotalId.text = convertPrice(grandPrice)
                        binding.discountTv.text = convertPrice(discount.value!!.toDouble())
                    }
                }
            }

        }
    }


    private fun toolbarConfig() {
        activity!!.findViewById<Toolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { findNavController().navigate(R.id.chooseAddressAndPaymentFragment) }
            setNavigationIcon(R.drawable.ic_arrow_back)
        }
    }

}