package com.example.bandapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bandapp.R
import com.example.bandapp.classes.Buddie
import kotlin.contracts.contract

class BuddieAdapter(private val userList: ArrayList<Buddie>): RecyclerView.Adapter<BuddieAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.buddiw_city_item,parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem= userList[position]

        holder.name.text= currentitem.name
        holder.city.text= currentitem.city
        holder.bio.text=currentitem.bio

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView= itemView.findViewById(R.id.tvBudName)
        var city: TextView= itemView.findViewById(R.id.tvBudCity)
        var bio: TextView= itemView.findViewById(R.id.tvBudBio)


    }

}