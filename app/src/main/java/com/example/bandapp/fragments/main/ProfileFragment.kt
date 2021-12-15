package com.example.bandapp.fragments.main

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bandapp.R
import com.example.bandapp.classes.Buddie
import com.example.bandapp.classes.Genre
import com.example.bandapp.classes.Instrument
import com.example.bandapp.classes.UserData
import com.example.bandapp.databinding.FragmentProfileBinding
import com.example.bandapp.utils.BuddieAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private val fileResult = 1

    private lateinit var  database: DatabaseReference

    var instruments= ArrayList<Instrument>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        binding= FragmentProfileBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var genres= ArrayList<Genre>()


        binding.profileImageView.setOnClickListener {
            fileManager()
        }


        binding.updateProfileAppCompatButton.setOnClickListener {
            val name= binding.nameEditText.text.toString()
            val mEmail= binding.emailTextView.text.toString()
            val mCity= binding.profileCity.text.toString()
            val mBio= binding.profileBio.text.toString()
            val fuser= UserData(name,mEmail,mCity,mBio,instruments,genres)

            database=FirebaseDatabase.getInstance().getReference("Profiles")
            database.child(name).setValue(fuser).addOnSuccessListener {

                Toast.makeText(context, "Actualizadoooo", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
                Toast.makeText(context, "Mehhh", Toast.LENGTH_SHORT).show()

            }
            updateProfile(name)
        }

        //activate instruments
        binding.profileGuitar.setOnClickListener {
            if(binding.profileGuitar.text == "Guitar"){
                binding.profileGuitar.text= "GUITAR"
                binding.profileGuitar.setTextColor(Color.parseColor("#0AAD3F"))
                instruments.add(Instrument("Guitar",true))

            } else {
                binding.profileGuitar.text= "Guitar"
                binding.profileGuitar.setTextColor(Color.parseColor("#c2b6b4"))
                instruments.add(Instrument("Guitar",false))
            }
        }

        binding.profileBassGuitar.setOnClickListener {
            if(binding.profileBassGuitar.text == "Bass Guitar"){
                binding.profileBassGuitar.text= "BASS GUITAR"
                binding.profileBassGuitar.setTextColor(Color.parseColor("#0AAD3F"))
                instruments.add(Instrument("Bass Guitar",true))

            } else {
                binding.profileBassGuitar.text= "Bass Guitar"
                binding.profileBassGuitar.setTextColor(Color.parseColor("#c2b6b4"))
                instruments.add(Instrument("Bass Guitar",false))

            }
        }

        binding.profileDrums.setOnClickListener {
            if(binding.profileDrums.text == "Drums"){
                binding.profileDrums.text= "DRUMS"
                binding.profileDrums.setTextColor(Color.parseColor("#0AAD3F"))
                instruments.add(Instrument("Guitar",true))

            } else {
                binding.profileDrums.text= "Drums"
                binding.profileDrums.setTextColor(Color.parseColor("#c2b6b4"))
                instruments.add(Instrument("Bass Guitar",false))

            }
        }

        binding.profileVoice.setOnClickListener {
            if(binding.profileVoice.text == "Voice"){
                binding.profileVoice.text= "VOICE"
                binding.profileVoice.setTextColor(Color.parseColor("#0AAD3F"))
                instruments.add(Instrument("Voice",true))

            } else {
                binding.profileVoice.text= "Voice"
                binding.profileVoice.setTextColor(Color.parseColor("#c2b6b4"))
                instruments.add(Instrument("Voice",false))

            }
        }

        binding.profilePiano.setOnClickListener {
            if(binding.profilePiano.text == "Piano"){
                binding.profilePiano.text= "PIANO"
                binding.profilePiano.setTextColor(Color.parseColor("#0AAD3F"))
                instruments.add(Instrument("Piano",true))

            } else {
                binding.profilePiano.text= "Piano"
                binding.profilePiano.setTextColor(Color.parseColor("#c2b6b4"))
                instruments.add(Instrument("Piano",false))

            }
        }

        //Activate genres
        binding.profileRock.setOnClickListener {
            if(binding.profileRock.text=="Rock"){
                binding.profileRock.text="ROCK"
                binding.profileRock.setTextColor(Color.parseColor("#0AAD3F"))
                genres.add(Genre("Rock",true))


            }else{
                binding.profileRock.text= "Rock"
                binding.profileRock.setTextColor(Color.parseColor("#c2b6b4"))
            }
        }

        binding.profileJazz.setOnClickListener {
            if(binding.profileJazz.text=="Jazz"){
                binding.profileJazz.text="JAZZ"
                binding.profileJazz.setTextColor(Color.parseColor("#0AAD3F"))
                genres.add(Genre("Jazz",true))

            }else{
                binding.profileJazz.text= "Jazz"
                binding.profileJazz.setTextColor(Color.parseColor("#c2b6b4"))
            }
        }
        binding.profileBlues.setOnClickListener {
            if(binding.profileBlues.text=="Blues"){
                binding.profileBlues.text="BLUES"
                binding.profileBlues.setTextColor(Color.parseColor("#0AAD3F"))
                genres.add(Genre("Blues",true))

            }else{
                binding.profileBlues.text= "Blues"
                binding.profileBlues.setTextColor(Color.parseColor("#c2b6b4"))
            }
        }
        binding.profileLatino.setOnClickListener {
            if(binding.profileLatino.text=="Latino"){
                binding.profileLatino.text="LATINO"
                binding.profileLatino.setTextColor(Color.parseColor("#0AAD3F"))
                genres.add(Genre("Latino",true))

            }else{
                binding.profileLatino.text= "Latino"
                binding.profileLatino.setTextColor(Color.parseColor("#c2b6b4"))
            }
        }
        binding.profileRyB.setOnClickListener {
            if(binding.profileRyB.text=="Cumbia-Regueton"){
                binding.profileRyB.text="CUMBIA-REGUETON"
                binding.profileRyB.setTextColor(Color.parseColor("#0AAD3F"))
                genres.add(Genre("Cumbia-Regueton",true))

            }else{
                binding.profileRyB.text= "Cumbia-Regueton"
                binding.profileRyB.setTextColor(Color.parseColor("#c2b6b4"))
            }
        }
        binding.profileOther.setOnClickListener {
            if(binding.profileOther.text=="Otros"){
                binding.profileOther.text="OTROS"
                binding.profileOther.setTextColor(Color.parseColor("#0AAD3F"))
                genres.add(Genre("Otros",true))

            }else{
                binding.profileOther.text= "Otros"
                binding.profileOther.setTextColor(Color.parseColor("#c2b6b4"))
            }
        }

        //Update the User interface > Images and Text
        updateUI()

    }

    override fun onStart() {
        super.onStart()
        //If user exists it get up to date profile information
        getProfile()
    }



    private fun updateProfile(name: String){
        val user= auth.currentUser
        val profileUpdates= userProfileChangeRequest {
            displayName= name
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    Toast.makeText(context, "Actualización exitosa", Toast.LENGTH_SHORT).show()
                    updateUI()
                }
            }
    }

    private fun fileManager() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, fileResult)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == fileResult) {
            if (resultCode == RESULT_OK && data != null) {
                val uri = data.data

                uri?.let { imageUpload(it) }

            }
        }
    }

    private fun imageUpload(mUri: Uri) {

        val user = auth.currentUser
        val folder: StorageReference = FirebaseStorage.getInstance().reference.child("Users")
        val fileName: StorageReference = folder.child("img"+user!!.uid)

        fileName.putFile(mUri).addOnSuccessListener {
            fileName.downloadUrl.addOnSuccessListener { uri ->

                val profileUpdates = userProfileChangeRequest {
                    photoUri = Uri.parse(uri.toString())
                }

                user.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Se realizaron los cambios correctamente.",
                                Toast.LENGTH_SHORT).show()
                            updateUI()
                        }
                    }
            }
        }.addOnFailureListener {
            Log.i("TAG", "file upload error")
        }
    }


    private fun updateUI(){
        val user= auth.currentUser

        if(user != null){
            binding.emailTextView.text = user.email

            if(user.displayName!=null){
                binding.nameTextView.text=user.displayName
                binding.nameEditText.setText(user.displayName)
            }


            Glide
                .with(this)
                .load(user.photoUrl)
                .centerCrop()
                .placeholder(R.drawable.logo)
                .into(binding.profileImageView)

            Glide
                .with(this)
                .load(user.photoUrl)
                .centerCrop()
                .placeholder(R.drawable.logo)
                .into(binding.bgProfileImageView)
        }
    }

    private fun getProfile(){
        if(binding.nameTextView.text=="My username"){
            Toast.makeText(context, "Actualizá tu nombre y ciudad", Toast.LENGTH_LONG).show()
        }else{
            val name= binding.nameTextView.text.toString()
            database=FirebaseDatabase.getInstance().getReference("Profiles")
            database.child(name).get().addOnSuccessListener {
                if(it.exists()){
                    binding.profileBio.hint= it.child("bio").value.toString()
                    binding.profileCity.hint=it.child("city").value.toString()
                    Toast.makeText(context, "Welcome back!", Toast.LENGTH_SHORT).show()
                }

            }.addOnFailureListener {
                Toast.makeText(context, "Error de conexión", Toast.LENGTH_SHORT).show()

            }
        }
    }


}