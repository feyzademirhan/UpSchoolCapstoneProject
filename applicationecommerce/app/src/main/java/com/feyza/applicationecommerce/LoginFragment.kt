package com.feyza.applicationecommerce

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.feyza.applicationecommerce.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(){

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var email : String
    private lateinit var password : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = Firebase.auth
        binding.forgotTextLine.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.forgotPasswordFragment)

        }

        context?.let{ FirebaseApp.initializeApp(it) }
        binding.loginButton.setOnClickListener{
            login(it)
        }
    }

    private fun login(view: View) {
        email = binding.emailLineText.text.toString().trim()
        password = binding.passwordLineText.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            showError("Enter your email!", "Try Again.", view)
        }
        else if (TextUtils.isEmpty(password)) {
            showError("Enter your password!", "Try Again.", view)
        } else {
            loginProcesses()
        }

    }

    private fun loginProcesses() {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(requireContext(),"Welcome!", Toast.LENGTH_SHORT).show()
                    if (view != null) {
                        Navigation.findNavController(view!!)
                            .navigate(R.id.action_loginFragment_to_mainActivity)
                        findNavController().popBackStack()
                    }
                    firebaseAuth.signOut()
                }else{
                    view?.let { showError("Failed login.", "Try again...", it) }
                }
            }
    }

    private fun showError(message: String, error: String, view: View) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackBar.setAction(error){
        }
        snackBar.setActionTextColor(Color.BLACK)
        snackBar.setTextColor(Color.BLACK)
        snackBar.setBackgroundTint(Color.WHITE)
        snackBar.show()

    }
}