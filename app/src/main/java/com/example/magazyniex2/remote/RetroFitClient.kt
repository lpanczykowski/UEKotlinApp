package com.example.magazyniex2.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetroFitClient {
    private  var instance :Retrofit?=null

    fun getInstance():Retrofit {
        if (instance == null)
            instance = Retrofit.Builder().baseUrl("http://localhost:5001/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return instance!!
    }

}