package com.example.magazyniex2.ui.gird

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.magazyniex2.R
import com.example.magazyniex2.data.Globals
import com.example.magazyniex2.data.model.Order
import com.example.magazyniex2.data.OrderAdapter
import com.example.magazyniex2.databinding.ActivityGridBinding
import com.example.magazyniex2.model.Login
import com.example.magazyniex2.remote.Api
import com.example.magazyniex2.ui.main.MainActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_grid.*

class GridActivity : AppCompatActivity() {
    private  lateinit var  orderAdapder : OrderAdapter
    private lateinit var binding: ActivityGridBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }


        orderAdapder = OrderAdapter(mutableListOf())
        binding = ActivityGridBinding.inflate(layoutInflater)
        val mainActivity = Intent(this, MainActivity::class.java)
        setContentView(binding.root)

        val btnBack = binding.ivBack;
        val imgTitle = binding.ivTitle;
        val b = intent.extras
        val type = b!!.getString("Type","XX")


        btnBack.setOnClickListener {
            startActivity(mainActivity);
        }

        this.title = ""
        rvGrid.adapter = orderAdapder
        rvGrid.layoutManager = LinearLayoutManager(this)

        val response = Api.performGetOperation("Orders/all")
        var gson = Gson()
        var Orders = gson?.fromJson(
            response,
            Array<Order>::class.java
        ).toList()


        if (type == "ZK") {
            imgTitle.setImageResource(R.drawable.titlezk)
        }
        //val sortedOrders =  Orders.sortedByDescending{
        //    it.Priority

       // }
        Orders.forEach {
                var item = Order(it.Number, it.Priority,it.IsRealized)
                orderAdapder.addItem(item)
            }

        if (type=="ZZ") {
            imgTitle.setImageResource(R.drawable.titlezz)
            var item = Order("ZZ/2021/1", 2)
            orderAdapder.addItem(item)
            item = Order("ZZ/2021/2", 2)
            orderAdapder.addItem(item)
            item = Order("ZZ/2021/3", 1)
            orderAdapder.addItem(item)
            item = Order("ZZ/2021/4", 3)
            orderAdapder.addItem(item)
        }


    }

    override fun onResume() {
        super.onResume()
        orderAdapder.remove(Globals.OrderRealized)
        Globals.OrderRealized =""

    }
}