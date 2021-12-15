package com.example.bandapp.classes

data class UserData
    (val name:String?="",
     val email:String?="",
     val city: String?="",
     val bio: String?="",
     val instruments: ArrayList<Instrument>,
     val genres: ArrayList<Genre>
    )

data class Instrument(
    val name: String?,
    val status: Boolean?
)

data class Genre(
    val name: String?,
    val status: Boolean
)

data class Buddie
    (val name:String?= null,
     val city: String?= null,
     val bio: String?=null
     )

