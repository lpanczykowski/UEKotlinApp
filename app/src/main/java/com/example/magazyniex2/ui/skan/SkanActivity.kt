package com.example.magazyniex2.ui.skan

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.magazyniex2.data.Globals
import com.example.magazyniex2.data.Globals.Companion.OrderRealized
import com.example.magazyniex2.data.model.SkanItem
import com.example.magazyniex2.data.SkanItemAdapter
import com.example.magazyniex2.data.model.OrderItem
import com.example.magazyniex2.databinding.ActivitySkanBinding
import com.example.magazyniex2.remote.Api
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_grid.*
import kotlinx.android.synthetic.main.activity_skan.*


private const val SECOND_ACTIVITY_REQUEST_CODE = 1


@Suppress("DEPRECATION")
class SkanActivity : AppCompatActivity() {
    private  lateinit var  itemAdapter : SkanItemAdapter
    private lateinit var binding: ActivitySkanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }



        itemAdapter = SkanItemAdapter(mutableListOf())
        binding = ActivitySkanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edtSkan = binding.edtScan
        val tvTitle = binding.tvTitle
        val btnQr =  binding.ibQR
        val btnOk = binding.btnOk
        var gson = Gson()
        edtSkan.requestFocus()
        val b = intent.extras
        val number  = b!!.getString("Title","Błąd")
        tvTitle.text=number;


        val qrActivity = Intent(this, QrActivity::class.java);
        rvScan.adapter = itemAdapter
        rvScan.layoutManager = LinearLayoutManager(this)
        val response = Api.performGetOperation("Orders/order?orderNumber=", number)
        if (response != "No items for this order" && response != null) {
            var skanItems = gson?.fromJson(
                response,
                Array<OrderItem>::class.java
            ).toList()

            skanItems.forEach {
                itemAdapter.addItem((it.Product));
            }
        }


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        btnQr.setOnClickListener {
            val intent = Intent(this, QrActivity::class.java)
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }
        btnOk.setOnClickListener{
            val response = Api.performPutOperation("Orders/realize?orderNumber=", number)
            OrderRealized = number;
            finish()
        }
        edtSkan.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    val response = Api.performGetOperation("Barcode/getInfo/", edtSkan.text.toString())
                    if (response != "Barcode not found" && response != null)
                    {
                        var skanItem = gson?.fromJson(
                            response,
                            SkanItem::class.java)
                        if (itemAdapter.Exists(skanItem))  {
                            itemAdapter.update(skanItem)
                        }
                        else{
                            itemAdapter.addItem(skanItem)
                            itemAdapter.update(skanItem)
                        }
                        edtSkan.text.clear();
                    }else {

                        Toast.makeText(this, "Błędny kod : ${edtSkan.text}", Toast.LENGTH_LONG).show()
                        edtSkan.text.clear();
                    }

                    return@OnKeyListener true
            }
            false
                })
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        binding = ActivitySkanBinding.inflate(layoutInflater)
        var gson = Gson()
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            val response = Api.performGetOperation("Barcode/getInfo/", Globals.CodeQr)
            if (response != "Barcode not found" && response != null)
            {
                var skanItem = gson?.fromJson(
                    response,
                    SkanItem::class.java)

                if (itemAdapter.Exists(skanItem))  {
                    itemAdapter.update(skanItem)
                }
                else{
                    itemAdapter.addItem(skanItem)
                    itemAdapter.update(skanItem)}
                Globals.CodeQr= "";
            }else {

                Toast.makeText(this, "Błędny kod : ${Globals.CodeQr}", Toast.LENGTH_LONG).show()
                Globals.CodeQr= "";
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}

