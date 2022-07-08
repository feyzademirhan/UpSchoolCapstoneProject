package com.feyza.applicationecommerce

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.feyza.applicationecommerce.databinding.FragmentForgotpasswordBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotpasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var email : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotpasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = Firebase.auth

        context?.let{ FirebaseApp.initializeApp(it) }
        binding.forgotButton.setOnClickListener{
            resetPassword(it)
        }
    }

    private fun resetPassword(view: View) {
        email = binding.emailLineText.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            showError("Enter your email!", "Try Again.", view)
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            showError("Wrong Email!", "Try Again.", view)

        } else {
            resetProcesses()
        }

    }

    private fun resetProcesses() {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    task.addOnSuccessListener {
                        Log.e("Reset","$it")
                }
                    Toast.makeText(requireContext(),"Check your email!", Toast.LENGTH_SHORT).show()
                }else{
                    task.addOnFailureListener {
                        Log.e("Resett","$it")
                    }
                    Toast.makeText(requireContext(),"Wrong email!", Toast.LENGTH_SHORT).show()

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