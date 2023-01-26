package com.example.appbancocomercio.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.appbancocomercio.BancoComercioApp
import com.example.appbancocomercio.BancoComercioApp.Companion.prefs
import com.example.appbancocomercio.data.database.BcDatabase
import com.example.appbancocomercio.data.database.dao.UserDao
import com.example.appbancocomercio.databinding.ActivityMainBinding
import com.example.appbancocomercio.ui.viewModel.PostsViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityMainBinding
    private val postViewModel: PostsViewModel by viewModels()
    private lateinit var  user : BancoComercioApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postViewModel.onCreate()
        binding.btnLogin.setOnClickListener {
            login()
        }
        if (prefs.getUser().isNotEmpty()) {
            showAlert()
        }
    }

    private fun checkUserValues(){
        startActivity(Intent(this, HomeActivity::class.java ))
    }

    private fun showAlert (){
        val userName = prefs.getUser()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Bienvenido $userName")
            .setMessage("Usted ya inició sesión anteriormente")
            .setPositiveButton("Ingresar"){ _, it->
                checkUserValues()
            }
            .create()
            .show()
    }
    private fun login() {
        lifecycleScope.launch {
            val email = binding.txtEmailLayoutText.text.toString()
            val password = binding.txtPassLayoutText.text.toString()

            if (validateForm(email, password)) {
                val room1 = Room
                    .databaseBuilder(applicationContext, BcDatabase::class.java, "usuarios")
                    .fallbackToDestructiveMigration()
                    .build()
                val userDao: UserDao = room1.userDao()
                val a = userDao.getUsers(email, password)
                if (a !=null) {
                    prefs.saveGmail(email)
                    prefs.savePassword(password)
                    goToHome()
                }
                else {
                    Toast.makeText(applicationContext, "Sus credenciales no son las correctas", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(applicationContext, "Ingrese sus credenciales", Toast.LENGTH_LONG).show()
            }
        }


    }
    private fun goToHome(){
        Toast.makeText(this, "Bienvenido!!", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, HomeActivity::class.java ))
    }
    private fun validateForm(email:String, password:String):Boolean{
        if (email.isBlank()){
            return false
        }
        if (password.isBlank()){
            return false
        }
        if (!email.isEmailValid()){
            return false
        }
        return true
    }
    private fun String.isEmailValid():Boolean{ //validacion para saber si el usuario ingreso el formato adecuado
        return !TextUtils.isEmpty(this)&& android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }


}