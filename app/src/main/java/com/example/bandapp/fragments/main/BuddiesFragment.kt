package com.example.bandapp.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bandapp.R
import com.example.bandapp.classes.Buddie
import com.example.bandapp.databinding.FragmentBuddiesBinding
import com.example.bandapp.utils.RecyclerViewAdapter


class BuddiesFragment : Fragment() {

    private lateinit var binding: FragmentBuddiesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentBuddiesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler= view.findViewById<RecyclerView>(R.id.recycler)
        val adapter= RecyclerViewAdapter()


        //Recycler configuration
        adapter.RecyclerViewAdapter(contacts(), requireContext())

        //Recycler configuration
        recycler.hasFixedSize()
        recycler.layoutManager= LinearLayoutManager(context)
        recycler.adapter= adapter

    }

    private fun contacts(): MutableList<Buddie> {
        var contactoModels: MutableList<Buddie> = ArrayList()
        contactoModels.add(Buddie("Juan","Buenos Aires"))
        contactoModels.add(Buddie("Ale","Córdoba"))
        contactoModels.add(Buddie("Juana","Buenos Aires"))
        contactoModels.add(Buddie("Sarah","Córdoba"))
        contactoModels.add(Buddie("Bea","Rosario"))
        contactoModels.add(Buddie("Sulma","Rosario"))
        contactoModels.add(Buddie("Jose","Buenos Aires"))
        contactoModels.add(Buddie("Jose","Buenos Aires"))
        contactoModels.add(Buddie("Jose","Buenos Aires"))
        contactoModels.add(Buddie("Jose","Buenos Aires"))
        contactoModels.add(Buddie("Jose","Buenos Aires"))
        contactoModels.add(Buddie("Jose","Buenos Aires"))
        contactoModels.add(Buddie("Jose","Buenos Aires"))

        return contactoModels
    }

}
