package com.example.bandapp.fragments.menu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bandapp.R
import com.example.bandapp.activities.MainActivity
import com.example.bandapp.activities.MenuActivity
import com.example.bandapp.databinding.FragmentCheckEmailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase


class CheckEmailFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentCheckEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth= Firebase.auth

        // Inflate the layout for this fragment
        binding= FragmentCheckEmailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = auth.currentUser

        binding.veficateEmailAppCompatButton.setOnClickListener {
            val profileUpdates= userProfileChangeRequest {}
                user!!.updateProfile(profileUpdates).addOnCompleteListener {task ->
                    if(task.isSuccessful){
                        if(user.isEmailVerified){
                            reload()
                        }else{
                            Toast.makeText(context, "Verifica tu correo para continuar", Toast.LENGTH_SHORT).show()
                        }
                    }

                }

        }

        binding.signOutImageView.setOnClickListener {
            signOut()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            if(currentUser.isEmailVerified){
                reload()
            }else{
                sendEmailVerification()
            }
        }
    }

    private fun sendEmailVerification(){
        val user= auth.currentUser
        user!!.sendEmailVerification().addOnCompleteListener(){task ->
            if(task.isSuccessful){
                Toast.makeText(context, "Se envio un correo de verificación", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun reload(){
        val intent= Intent(context, MainActivity::class.java)
        this.startActivity(intent)
    }

    private fun signOut(){
        Firebase.auth.signOut()
        val intent= Intent(context, MenuActivity::class.java)
        startActivity(intent)

    }

}