package com.feyza.applicationecommerce

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.feyza.applicationecommerce.databinding.FragmentSignupBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var email : String
    private lateinit var password : String
    private lateinit var name :String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = Firebase.auth
        binding.accountTextLine.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.loginFragment)

        }
        context?.let{ FirebaseApp.initializeApp(it) }
        binding.signupButton.setOnClickListener{
            validateData(it)
        }
    }

    private fun validateData(view: View) {
        name = binding.nameLineText.text.toString().trim()
        email = binding.emailLineText.text.toString().trim()
        password = binding.passwordLineText.text.toString().trim()

        if (TextUtils.isEmpty(name)) {
            showError("Enter your name!", "Try Again.", view)
        }
        else if (TextUtils.isEmpty(email)){
            showError("Enter your email!", "Try Again.", view)

        }
        else if (TextUtils.isEmpty(password)) {
            showError("Enter your password!", "Try Again.", view)
        } else {
            createUserAccount()
        }

    }

    private fun createUserAccount() {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val dataBase = FirebaseFirestore.getInstance()
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    view?.let { showError("Account created.", "Try Again.", it) }
                    view?.let { Navigation.findNavController(it).navigate(R.id.loginFragment) }
                }else{
                    view?.let { showError("Failed creating account!", "Try again...", it) }
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