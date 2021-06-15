package com.example.magazyniex2.remote

import android.os.StrictMode
import com.example.magazyniex2.utils.Constants.Companion.BASE_URL
import okhttp3.FormBody
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException
import java.net.URL



object Api {


     fun performGetOperation(url:String, arg:String = ""): String? {
         return try  {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val client = OkhttpClient.okhttp3
            val request = Request.Builder()
                .url(URL(BASE_URL+url+arg))
                .get()
                .build()
            val response = client.newCall(request).execute()
            response.body()?.string()
        }
        catch (e: IOException) {
            return null
        }
    }
    fun performPutOperation(url:String, arg:String = ""): String? {
        return try  {
            val formBody: RequestBody = FormBody.Builder()
                .add("", "")
                .build()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val client = OkhttpClient.okhttp3
            val request = Request.Builder()
                .url(URL(BASE_URL+url+arg))
                .put(formBody)
                .build()
            val response = client.newCall(request).execute()
            response.body()?.string()

        }
        catch (e: IOException) {
            return null
        }
    }
}