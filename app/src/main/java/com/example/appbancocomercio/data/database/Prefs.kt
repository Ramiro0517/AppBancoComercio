package com.example.appbancocomercio.data.database

import android.content.Context

class Prefs(val context:Context) {
    val SHARED_NAME= "Mydatabase"
    val SHARED_USER = "loginUser"
    val SHARED_PASSWORD = "PasswordUser"

    val storage= context.getSharedPreferences(SHARED_NAME,0)

    fun saveGmail(gmail:String){
        storage.edit().putString(SHARED_USER, gmail).apply()
    }
    fun savePassword(password:String){
        storage.edit().putString(SHARED_PASSWORD, password).apply()
    }

    fun getUser():String{
        return storage.getString(SHARED_USER,"")!!
    }
    fun getPassword():String{
        return storage.getString(SHARED_PASSWORD,"")!!
    }
    fun clear(){
        storage.edit().clear().apply()
    }
}