package com.example.magazyniex2.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.magazyniex2.R
import com.example.magazyniex2.databinding.ActivityLoginBinding
import com.example.magazyniex2.databinding.ActivityMainBinding
import com.example.magazyniex2.ui.gird.GridActivity
import com.example.magazyniex2.ui.login.LoginActivity
import com.example.magazyniex2.ui.login.LoginViewModel
import com.example.magazyniex2.ui.login.LoginViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnLogout = binding.btnLogout;
        val btnWZ = binding.btnWZ;
        val loginActivity = Intent(this,LoginActivity::class.java);
        val WZActivity = Intent(this,GridActivity::class.java);
        val b = intent.extras
        this.title = b!!.getString("Name","Username")

        btnLogout.setOnClickListener {
                Toast.makeText(
                    applicationContext,
                    "Wylogowanie",
                    Toast.LENGTH_LONG
                ).show()
                startActivity(loginActivity)
        }
        btnWZ.setOnClickListener {
            startActivity(WZActivity)
        }
    }
}