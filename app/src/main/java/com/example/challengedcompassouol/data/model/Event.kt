package com.example.challengedcompassouol.data.model

data class Event (
    val date: String,
    val description: String,
    val image: String,
    val longitude: String,
    val latitude: String,
    val price: Double,
    val title: String,
    val id: Int
    ){
    fun getEventModel() = Event(
        date = this.date,
        description = this.description,
        image = this.image,
        longitude = this.longitude,
        latitude = this.latitude,
        price = this.price,
        title = this.title,
        id = this.id
    )
}