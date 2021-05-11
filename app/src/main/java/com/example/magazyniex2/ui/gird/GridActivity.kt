package com.example.magazyniex2.ui.gird

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.magazyniex2.R
import com.example.magazyniex2.data.Item
import com.example.magazyniex2.data.ItemAdapter
import com.example.magazyniex2.databinding.ActivityGridBinding
import com.example.magazyniex2.databinding.ActivityMainBinding
import com.example.magazyniex2.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_grid.*

class GridActivity : AppCompatActivity() {
    private  lateinit var  itemAdapder : ItemAdapter
    private lateinit var binding: ActivityGridBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)


        itemAdapder = ItemAdapter(mutableListOf())
        binding = ActivityGridBinding.inflate(layoutInflater)
        val mainActivity = Intent(this, MainActivity::class.java)
        setContentView(binding.root)

        val btnBack = binding.ivBack;

        btnBack.setOnClickListener {
            startActivity(mainActivity);
        }

        this.title = ""
        rvGrid.adapter = itemAdapder
        rvGrid.layoutManager = LinearLayoutManager(this)

        var item = Item("Dummy Data",2)
        itemAdapder.addItem(item)

        item = Item("Dummy Data 2",3)
        itemAdapder.addItem(item)

        item = Item("Dummy Data 3",1)
        itemAdapder.addItem(item)
        itemAdapder.addItem(item)
        itemAdapder.addItem(item)
        itemAdapder.addItem(item)
        itemAdapder.addItem(item)
        itemAdapder.addItem(item)
        itemAdapder.addItem(item)
        itemAdapder.addItem(item)
        itemAdapder.addItem(item)
        itemAdapder.addItem(item)


    }
}