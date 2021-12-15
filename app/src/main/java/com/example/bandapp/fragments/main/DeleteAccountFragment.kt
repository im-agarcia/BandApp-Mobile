package com.example.bandapp.fragments.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bandapp.R
import com.example.bandapp.activities.MenuActivity
import com.example.bandapp.databinding.FragmentDeleteAccountBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class DeleteAccountFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentDeleteAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        auth = Firebase.auth

        binding= FragmentDeleteAccountBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deleteAccountAppCompatButton.setOnClickListener {
            val password = binding.passwordEditText.text.toString()
            deleteAccount (password)
        }

    }

    private  fun deleteAccount(password : String) {
        val user = auth.currentUser

        if (user != null){
            val email = user.email
            val credential = EmailAuthProvider
                .getCredential(email!!, password)

            user.reauthenticate(credential)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {

                        user.delete()
                            .addOnCompleteListener { taskDeleteAcount ->
                                if (taskDeleteAcount.isSuccessful) {
                                    Toast.makeText(context, "Te vamos a extrañar",
                                        Toast.LENGTH_SHORT).show()
                                    signOut()
                                }
                            }

                    } else {
                        Toast.makeText(context, "La contraseña es incorrect.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private  fun signOut(){
        auth.signOut()
        val intent = Intent(context, MenuActivity::class.java)
        this.startActivity(intent)
    }

}