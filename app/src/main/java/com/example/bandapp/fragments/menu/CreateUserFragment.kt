package com.example.bandapp.fragments.menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bandapp.R
import com.example.bandapp.activities.MainActivity
import com.example.bandapp.activities.MenuActivity
import com.example.bandapp.databinding.FragmentCreateUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern


class CreateUserFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentCreateUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth= Firebase.auth

        // Inflate the layout for this fragment
        binding = FragmentCreateUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener {
            val mEmail= binding.emailEditText.text.toString()
            val mPassword= binding.passwordEditText.text.toString()
            val mRepeatPassword= binding.repeatPasswordEditText.text.toString()

            val passwordRegex= Pattern.compile(
                "^" +
                        "(?=.*[-@#$^&+=])" + //at least a special character
                        ".{6,}" +  //at least 6 characters
                        "$")

            if(mEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()){
                Toast.makeText(context, "Ingrese un email válido",
                    Toast.LENGTH_SHORT).show()
            }else if(mPassword.isEmpty() || !passwordRegex.matcher(mPassword).matches()){
                Toast.makeText(context, "Contraseña debil",
                    Toast.LENGTH_SHORT).show()
            }else if(mPassword!=mRepeatPassword){
                Toast.makeText(context, "Las contraseñas no coinciden",
                    Toast.LENGTH_SHORT).show()
            }else{
                createAccount(mEmail,mPassword)
            }
        }

        binding.backImageView.setOnClickListener {
            val intent= Intent(context, MenuActivity::class.java)
            this.startActivity(intent)
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            if(currentUser.isEmailVerified){
                reload();
            }
        }
    }

    private fun createAccount(email: String, password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_createUserFragment_to_checkEmailFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun reload(){
        val intent= Intent(context, MainActivity::class.java)
        this.startActivity(intent)
    }

}