package com.example.gocart.auth.register_signup
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gocart.auth.pojo.Customer
import com.example.gocart.auth.pojo.CustomerModel
import com.example.gocart.databinding.FragmentRegisterBinding


class RegisterFragment :Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    var firstName:String?= null
    var userEmail:String?= null
    var userPassword:String?= null
    var userConfirmPassword:String?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//            binding.tvSignin.setOnClickListener {
//            findNavController().navigate(R.id.)
//            }
        binding.registerBtn.setOnClickListener {
            if (validate()){

                val customer = CustomerModel(
                   Customer(
                       firstName = firstName,
                       lastName = userPassword,
                       email = userEmail,
                       password = userPassword,
                       passwordConfirmation = userConfirmPassword
                   )
                )
                registerViewModel.postData(customer)
                registerViewModel.signupSuccess.observe(viewLifecycleOwner){
                    if (it!!){
                        Toast.makeText(requireContext(),"Registered Successfully",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(requireContext(),"Unsuccessful Register", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }


    }
    private fun validate(): Boolean{
        firstName =binding.nameEdt.text.toString()
        userEmail=binding.emailEdt.text.toString()
        userPassword=binding.passwordEdt.text.toString()
        userConfirmPassword=binding.confirmPasswordEdt.text.toString()

        if (firstName!!.isEmpty()){
            binding.nameEdt.requestFocus()
            binding.nameEdt.error="User Name is Required"
            return false
        }

        if (userEmail!!.isEmpty()){
            binding.emailEdt.requestFocus()
            binding.emailEdt.error="Email Address is Required"
            return false
        }

        if (userPassword!!.isEmpty()){
            binding.passwordEdt.requestFocus()
            binding.passwordEdt.error="Password is Required"
            return false
        }

        if (userConfirmPassword!!.isEmpty()){
            binding.confirmPasswordEdt.requestFocus()
            binding.confirmPasswordEdt.error="Confirm Password is Required"
            return false
        }

        if (!userPassword.equals(userConfirmPassword)){
            binding.confirmPasswordEdt.requestFocus()
            binding.confirmPasswordEdt.error="Password Doesn't Match With Confirm Password"
            return false
        }
        return true
    }




}
