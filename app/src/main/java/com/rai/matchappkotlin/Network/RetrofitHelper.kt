package com.rai.matchappkotlin.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
 
object RetrofitHelper {

    val baseUrl = "https://demo.sportz.io"


    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}