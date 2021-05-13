package com.example.magazyniex2.remote

import com.example.magazyniex2.model.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface IApi {
        @POST("login")
        fun loginUser(@Body user: User): Observable<String>
    }