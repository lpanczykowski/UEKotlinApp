package com.example.magazyniex2.data.model

data class SkanItem (
    val Code: String,
    val ItemName: String,
    var Quantity: Long,
    val Units: String,
    val Batch: String,
    var Scanned : Long = 0
)