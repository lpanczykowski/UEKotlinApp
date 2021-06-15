package com.example.magazyniex2.data.model

data class Order(
    val Number: String,
    val Priority: Int,
    var IsRealized :Boolean = false
)
