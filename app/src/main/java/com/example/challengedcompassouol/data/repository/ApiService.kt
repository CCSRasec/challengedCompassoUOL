package com.example.challengedcompassouol.data.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {
    private fun initRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
//            .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
    }

    val service: APIEventos = initRetrofit().create(APIEventos::class.java)
}