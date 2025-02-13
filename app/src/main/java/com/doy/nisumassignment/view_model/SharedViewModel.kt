package com.doy.nisumassignment.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doy.nisumassignment.model.User
import com.doy.nisumassignment.model.UsersApiResponse
import com.doy.nisumassignment.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SharedViewModel : ViewModel() {


    private val _usersList: MutableLiveData<List<User>> = MutableLiveData<List<User>>(null)

    val usersList: LiveData<List<User>>
        get() = _usersList

    private val _selectedUser = MutableLiveData<User>()
    val selectedUser: LiveData<User> = _selectedUser


    private val _responseFailed = MutableLiveData<String>()
    val responseFailed: LiveData<String> = _responseFailed

    fun updateSelectedUser(user: User) {
        _selectedUser.value = user
    }

    fun updateResponseFailed(message: String) {
        _responseFailed.value = message
    }

    fun fetchUsers(usersCount: Int = 2) {
        _usersList.value = emptyList()
        val call = RetrofitClient.ApiClient.apiService.getUsers(usersCount)
        println("Request  = ${call.request()}")
        call.enqueue(object : Callback<UsersApiResponse> {
            override fun onResponse(
                call: Call<UsersApiResponse>,
                response: Response<UsersApiResponse>
            ) {
                if (response.isSuccessful) {

                    val post: UsersApiResponse? = response.body()

                    if (post != null) {
                        _usersList.value = post.results
                    }

                    println("ON_RESPONSE =: ${_usersList.value}")

                } else {
                    println("ON_RESPONSE Fail= ${response.code()}")
                }
            }

            override fun onFailure(call: Call<UsersApiResponse>, t: Throwable) {
                updateResponseFailed("${t.message}")
                println("ON_FAILURE = ${t.message}")
            }
        })

    }


}