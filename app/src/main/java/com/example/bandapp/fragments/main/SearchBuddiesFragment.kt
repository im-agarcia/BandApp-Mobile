package com.example.bandapp.fragments.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bandapp.R
import com.example.bandapp.classes.Buddie
import com.example.bandapp.databinding.FragmentSearchBuddiesBinding
import com.example.bandapp.utils.BuddieAdapter
import com.google.firebase.database.*


class SearchBuddiesFragment : Fragment() {

    private lateinit var binding: FragmentSearchBuddiesBinding

    private lateinit var dbRef: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<Buddie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentSearchBuddiesBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecyclerView= view.findViewById(R.id.recyclerBuddieSearch)
        userRecyclerView.layoutManager= LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)
        userArrayList= arrayListOf<Buddie>()

        binding.searchBuddie.setOnClickListener {
            val cityRequest= binding.etBuddieCity.text.toString()
            getUserData(cityRequest)
        }
    }


    //get data from Firebase and fetch with Buddie class
    private fun getUserData(request: String) {
        dbRef= FirebaseDatabase.getInstance().getReference("Profiles")
        dbRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //store all data fetch
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val bud= userSnapshot.getValue(Buddie:: class.java)
                        if(bud?.city == request){
                            userArrayList.add(bud!!)//if this object is null will throw an exception
                        }
                    }

                    userRecyclerView.adapter= BuddieAdapter(userArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,"Error de conexión",Toast.LENGTH_SHORT).show()
            }

        })

    }


}