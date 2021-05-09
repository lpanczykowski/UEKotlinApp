package com.example.magazyniex2.ui.skan

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.magazyniex2.R
import com.example.magazyniex2.databinding.ActivitySkanBinding

class SkanActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySkanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySkanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edtSkan = binding.edtScan
        val tvTitle = binding.tvTitle
        edtSkan.requestFocus()
        val b = intent.extras
        val title = b!!.getString("Title","Błąd")
        tvTitle.text = title;

        edtSkan.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    Toast.makeText(
                        applicationContext,
                        "Odczytanie kodu",
                        Toast.LENGTH_LONG
                    ).show()
                    return@OnKeyListener true
            }

            false
                })
        }
    }

