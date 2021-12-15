package com.example.bandapp.fragments.menu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bandapp.R
import com.example.bandapp.activities.MenuActivity
import com.example.bandapp.databinding.FragmentPasswordResetBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class PasswordResetFragment : Fragment() {

    private lateinit var binding: FragmentPasswordResetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentPasswordResetBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.senEmailAppCompatButton.setOnClickListener {
            val emailAddress= binding.emailEditText.text.toString()
            Firebase.auth.sendPasswordResetEmail(emailAddress).addOnCompleteListener { task->
                if(task.isSuccessful){
                    val intent= Intent(context,MenuActivity::class.java)
                    this.startActivity(intent)
                }else{
                    Toast.makeText(context,"Datos ingresados incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


}