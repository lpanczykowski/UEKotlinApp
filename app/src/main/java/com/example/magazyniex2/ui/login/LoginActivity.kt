package com.example.magazyniex2.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.magazyniex2.MainViewModel
import com.example.magazyniex2.MainViewModelFactory
import com.example.magazyniex2.data.Globals
import com.example.magazyniex2.data.model.SkanItem
import com.example.magazyniex2.databinding.ActivityLoginBinding
import com.example.magazyniex2.databinding.ActivitySkanBinding
import com.example.magazyniex2.model.Login
import com.example.magazyniex2.remote.Api
import com.example.magazyniex2.remote.Api.performGetOperation
import com.example.magazyniex2.repository.Repository
import com.example.magazyniex2.ui.main.MainActivity
import com.example.magazyniex2.ui.skan.QrActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.util.*



private const val SECOND_ACTIVITY_REQUEST_CODE = 2

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityLoginBinding
    private val client = OkHttpClient()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val password = binding.etPassword;
        val progressBar = binding.pbLogin;
        val MainActivity = Intent(this, MainActivity::class.java)
        val repository = Repository()
        val btnQr =  binding.ibQR
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        this.title = "Logowanie"
        var gson = Gson()



        btnQr.setOnClickListener {
            val intent = Intent(this, QrActivity::class.java)
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }


        password.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                progressBar.visibility=View.VISIBLE
                val response = performGetOperation("login/",password.text.toString())

                if (response != "Wrong login or password"  && response!= null)
                {
                    var LoggedUser = gson?.fromJson(
                    response,
                    Login::class.java)
                    Toast.makeText(this, "Witaj ${LoggedUser.Firstname}", Toast.LENGTH_LONG).show()
                    Globals.Username = LoggedUser.Firstname;
                    startActivity(MainActivity)

                }
                else
                    Toast.makeText(this, "Błędne hasło : ${password.text.toString()}", Toast.LENGTH_LONG).show()
                    progressBar.visibility=View.INVISIBLE
                return@OnKeyListener true
            } else
                false
        })

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val MainActivity = Intent(this, MainActivity::class.java)
        var gson = Gson()
        if (requestCode == com.example.magazyniex2.ui.login.SECOND_ACTIVITY_REQUEST_CODE) {
            val response = performGetOperation("login/",Globals.CodeQr)
            if (response != "Wrong login or password"  && response!= null) {
                var LoggedUser = gson?.fromJson(
                    response,
                    Login::class.java
                )
                Toast.makeText(this, "Witaj ${LoggedUser.Firstname}", Toast.LENGTH_LONG).show()
                Globals.Username = LoggedUser.Firstname;
                startActivity(MainActivity)


            }else
            {
                Toast.makeText(this, "Błędne hasło : ${Globals.CodeQr}", Toast.LENGTH_LONG).show()}
                Globals.CodeQr= "";

        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }



}



