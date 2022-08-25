package com.mugdha.notepadApplication.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mugdha.notepadApplication.R
import com.mugdha.notepadApplication.adapterFiles.CardInfo
import com.mugdha.notepadApplication.adapterFiles.DataObject
import com.mugdha.notepadApplication.databaseFiles.myDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
//        database = Room.databaseBuilder(
//            applicationContext, myDatabase::class.java, "To_Do"
//        ).build()

        database=myDatabase.getDatabase(application)

        GlobalScope.launch{
           DataObject.listdata = database.dao().getTask() as MutableList<CardInfo>
        }
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, one::class.java)
            startActivity(intent)
        }, 2000)
    }
}