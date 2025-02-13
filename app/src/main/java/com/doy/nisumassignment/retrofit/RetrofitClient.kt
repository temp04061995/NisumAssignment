package com.doy.nisumassignment.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://randomuser.me/"


    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    object ApiClient {
        val apiService: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }
    }

}