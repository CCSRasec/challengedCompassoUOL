package com.example.challengedcompassouol.data.repository

import com.example.challengedcompassouol.data.model.Event
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIEventos {
    @GET("api/events")
    fun getEvents(): Call<List<Event>>
}