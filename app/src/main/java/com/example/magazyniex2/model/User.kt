package com.example.magazyniex2.model

class User {
    var username: String?=null
    var password: String?=null


    constructor()
    constructor(username: String?, password: String?) {
        this.password = password
        this.username = username
    }


}