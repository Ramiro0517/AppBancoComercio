package com.example.appbancocomercio.ui.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appbancocomercio.BancoComercioApp.Companion.prefs
import com.example.appbancocomercio.adapters.PostsAdapter
import com.example.appbancocomercio.data.model.PostsModel
import com.example.appbancocomercio.data.model.PostsProvider
import com.example.appbancocomercio.databinding.ActivityHomeBinding
import java.util.*


class HomeActivity : AppCompatActivity() {

    private lateinit var  homeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)


        initRecyclerView()
        initUI()

    }

    private fun initUI(){
        val userName = prefs.getUser()
        homeBinding.tvWelcomeName.text="$userName"
        homeBinding.btnClose.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("¿Seguro que desea cerrar sesión?")
                .setMessage("Si continua, deberá ingresar nuevamente con sus credenciales")
                .setPositiveButton("Si"){ _, it->
                    prefs.clear()
                    onBackPressed()
                }
                .setNegativeButton("No",null )
                .create()
                .show()

        }

    }
    private fun initRecyclerView(){
        val manager= LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this,manager.orientation )

        homeBinding.rvPosts.layoutManager = manager
        homeBinding.rvPosts.adapter = PostsAdapter(PostsProvider.posts) { onItemSelected(it) }
        homeBinding.rvPosts.addItemDecoration(decoration)
    }

    private fun onItemSelected(posts: PostsModel){
    Toast.makeText(this, posts.name, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        val time = 15*60*1000
        Handler().postDelayed(Runnable {
            val i = Intent(this, MainActivity::class.java)
            this.startActivity(i)
            this.finish()
        }, time.toLong())
    }

    override fun onStop() {
        super.onStop()
        val time = 15*60*1000
        Handler().postDelayed(Runnable {
            val i = Intent(this, MainActivity::class.java)
            this.startActivity(i)
            this.finish()
        }, time.toLong())
    }
}