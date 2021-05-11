package com.example.magazyniex2.ui.skan

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.magazyniex2.R
import com.example.magazyniex2.data.ItemAdapter
import com.example.magazyniex2.data.SkanItem
import com.example.magazyniex2.data.SkanItemAdapter
import com.example.magazyniex2.databinding.ActivitySkanBinding
import kotlinx.android.synthetic.main.activity_grid.*
import kotlinx.android.synthetic.main.activity_skan.*

class SkanActivity : AppCompatActivity() {
    private  lateinit var  itemAdapter : SkanItemAdapter
    private lateinit var binding: ActivitySkanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skan)

        itemAdapter = SkanItemAdapter(mutableListOf())
        binding = ActivitySkanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edtSkan = binding.edtScan
        val tvTitle = binding.tvTitle
        edtSkan.requestFocus()
        val b = intent.extras
        tvTitle.text  = b!!.getString("Title","Błąd")

        rvScan.adapter = itemAdapter
        rvScan.layoutManager = LinearLayoutManager(this)

        var Skanitem = SkanItem("DUMMY",100,"11111","KG")
        itemAdapter.addItem(Skanitem);

        edtSkan.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    Toast.makeText(
                        applicationContext,
                        "Odczytanie kodu",
                        Toast.LENGTH_LONG
                    ).show()
                    var Skanitem = SkanItem("DUMMY",100,"11111","KG")
                    itemAdapter.addItem(Skanitem);
                    return@OnKeyListener true
            }

            false
                })
        }
    }

