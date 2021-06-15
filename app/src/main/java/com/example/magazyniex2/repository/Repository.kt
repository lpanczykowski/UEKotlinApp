package com.example.magazyniex2.repository

import com.example.magazyniex2.model.Login
import com.example.magazyniex2.remote.OkhttpClient

class Repository {

    suspend fun loginUser(password : String) :Login{
        return OkhttpClient.api.loginUser(password)
    }
}