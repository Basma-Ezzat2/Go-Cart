//package com.example.gocart.auth.register_login
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.navigation.fragment.findNavController
//import com.example.gocart.R
//import com.example.gocart.auth.register_signup.RegisterViewModel
//import com.example.gocart.databinding.FragmentLoginBinding
//import com.example.gocart.databinding.FragmentRegisterBinding
//
//class LoginFragment : Fragment() {
//
//    var userEmail: String? = null
//    var userPass: String? = null
//    private lateinit var binding: FragmentLoginBinding
//    private lateinit var loginViewModel: LoginViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentLoginBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        binding.tvSignup.setOnClickListener {
////            findNavController().navigate(R.id)
////        }
//        /* binding.loginBtn.setOnClickListener {
//             if (validate()){
//                 loginViewModel.getData(userEmail!!)
//                 loginViewModel.loginSuccess.observe(viewLifecycleOwner){
//                     if (it!!){
//                         Toast.makeText(requireContext(), "Logged in Successfully", Toast.LENGTH_LONG).show()
//
//                     }
//                 }
//             }
//             }
//         }*/
//
//    }
//}
//
//
//
//
////   private fun validate():Boolean{
//    //userEmail=binding.emailEdt.text.toString()
//
////}