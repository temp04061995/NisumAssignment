package com.doy.nisumassignment

import com.doy.nisumassignment.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("?results=10")
    fun getUsers(): Call<ApiResponse>

}