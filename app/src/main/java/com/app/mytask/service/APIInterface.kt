package com.app.mytask.service

import com.app.mytask.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("/repositories")
    fun doGetUserList(@Query("since") nextId: Int): Call<List<UserResponse>>
}