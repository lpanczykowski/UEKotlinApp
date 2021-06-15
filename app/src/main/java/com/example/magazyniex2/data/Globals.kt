package com.example.magazyniex2.data

import android.app.Application

class Globals :Application() {
    companion object{
        var Username: String ="default"
        var CodeQr: String = ""
        var OrderRealized : String = "";
    }
}