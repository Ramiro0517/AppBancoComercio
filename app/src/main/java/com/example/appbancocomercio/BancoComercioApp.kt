package com.example.appbancocomercio

import android.app.Application
import com.example.appbancocomercio.data.database.Prefs


class BancoComercioApp: Application() {

    companion object{
       lateinit var prefs: Prefs
    }
    override fun onCreate() {
        super.onCreate()
         prefs =Prefs(applicationContext)
    }
}