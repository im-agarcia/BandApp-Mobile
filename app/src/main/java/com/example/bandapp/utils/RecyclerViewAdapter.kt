package com.example.bandapp.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bandapp.R
import com.example.bandapp.classes.Buddie
import com.example.bandapp.fragments.main.BuddiesFragment

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    var contactos: MutableList<Buddie> = ArrayList()

    lateinit var context: Context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val bName: TextView= view.findViewById(R.id.tvBuddieName)
        val bCity: TextView= view.findViewById(R.id.tvBuddieCity)

    }

    fun RecyclerViewAdapter(contactos:MutableList<Buddie>, context: Context){
        this.contactos=contactos
        this.context=context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bName.text = contactos[position].name
        holder.bCity.text = contactos[position].city

    }

    override fun getItemCount()= contactos.size

}