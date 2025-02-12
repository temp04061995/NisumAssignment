package com.doy.nisumassignment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.doy.nisumassignment.model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val call = RetrofitClient.ApiClient.apiService.getUsers()

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    println("ON_RESPONSE = $post")
                    // Handle the retrieved post data
                } else {
                    println("ON_RESPONSE Fail= ${response.code()}")
                    // Handle error
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("ON_FAILURE = ${t.message}")
                // Handle failure
            }
        })


    }
}