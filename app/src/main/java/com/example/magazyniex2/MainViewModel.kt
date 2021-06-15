package com.example.magazyniex2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magazyniex2.model.Login
import com.example.magazyniex2.model.User
import com.example.magazyniex2.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private  val repository: Repository):ViewModel() {

    private val myResponse : MutableLiveData<Login> = MutableLiveData()
    fun loginUser(password : String) {
        viewModelScope.launch {
            val response = repository.loginUser(password)
            myResponse.value = response
        }

    }
}