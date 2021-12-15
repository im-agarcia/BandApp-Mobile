package com.example.bandapp.fragments.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bandapp.R
import com.example.bandapp.activities.MainActivity
import com.example.bandapp.databinding.FragmentPasswordModificationBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern


class PasswordModificationFragment : Fragment() {

    private lateinit var binding: FragmentPasswordModificationBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        // Inflate the layout for this fragment
        binding= FragmentPasswordModificationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val passwordRegex = Pattern.compile("^" +
                "(?=.*[-@#$%^&+=])" +
                ".{6,}" +
                "$")

        binding.changePasswordAppCompatButton.setOnClickListener {
            val currentPassword = binding.currentPasswordEditText.text.toString()
            val newPassword = binding.newPasswordEditText.text.toString()
            val repeatPassword = binding.repeatPasswordEditText.text.toString()

            if (newPassword.isEmpty() || !passwordRegex.matcher(newPassword).matches()){
                Toast.makeText(context, "La contraseña es débil",
                    Toast.LENGTH_SHORT).show()
            } else if (newPassword != repeatPassword){
                Toast.makeText(context, "Confirmá la contraseña",
                    Toast.LENGTH_SHORT).show()
            } else {
                changePassword(currentPassword, newPassword)
            }
        }

    }



private  fun changePassword(current : String, password : String) {
    val user = auth.currentUser

    if (user != null) {
        val email = user.email
        val credential = EmailAuthProvider
            .getCredential(email!!, current)

        user.reauthenticate(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    user.updatePassword(password)
                        .addOnCompleteListener { taskUpdatePassword ->
                            if (taskUpdatePassword.isSuccessful) {
                                Toast.makeText(
                                    context, "Se cambió la contraseña",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(context, MainActivity::class.java)
                                this.startActivity(intent)
                            }
                        }

                } else {
                    Toast.makeText(
                        context, "La contraseña actual es incorrecta",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


}
}