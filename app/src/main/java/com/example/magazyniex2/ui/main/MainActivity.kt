package com.example.magazyniex2.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.magazyniex2.R
import com.example.magazyniex2.data.Globals
import com.example.magazyniex2.databinding.ActivityLoginBinding
import com.example.magazyniex2.databinding.ActivityMainBinding
import com.example.magazyniex2.ui.gird.GridActivity
import com.example.magazyniex2.ui.login.LoginActivity
import com.example.magazyniex2.ui.login.LoginViewModel
import com.example.magazyniex2.ui.login.LoginViewModelFactory
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnLogout = binding.btnLogout;
        val btnWZ = binding.btnWZ;
        val btnZZ = binding.btnZZ;
        val loginActivity = Intent(this,LoginActivity::class.java);
        val gridActivity = Intent(this,GridActivity::class.java);
        this.title="Witaj, " + Globals.Username;
        btnLogout.setOnClickListener {
                Toast.makeText(
                    applicationContext,
                    "Wylogowanie",
                    Toast.LENGTH_LONG
                ).show()
                startActivity(loginActivity)
        }
        btnWZ.setOnClickListener {
            gridActivity.putExtra("Type","ZK")
            startActivity(gridActivity);
        }
        btnZZ.setOnClickListener{
            gridActivity.putExtra("Type","ZZ")
            startActivity(gridActivity);
        }
    }
}