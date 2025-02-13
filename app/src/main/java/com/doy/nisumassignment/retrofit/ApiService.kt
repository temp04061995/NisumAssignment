package com.doy.nisumassignment.retrofit

import com.doy.nisumassignment.model.UsersApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    fun getUsers(@Query("results") count : Int = 3): Call<UsersApiResponse>

}