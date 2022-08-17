package com.mugdha.notepadApplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.mugdha.notepadApplication.adapterFiles.DataObject
import com.mugdha.notepadApplication.databaseFiles.Entity
import com.mugdha.notepadApplication.R
import com.mugdha.notepadApplication.databaseFiles.myDatabase
import kotlinx.android.synthetic.main.activity_create_card.*
import kotlinx.android.synthetic.main.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        //starting of demo code --->
        //for profiler
        for(i in 1..1000) {
            val a = (100..1000).random()
            val b = (0..2).random()
           // val c= getRandomWords()
            //get random word from list
           Log.v("Mugdha", "$a + $b = ${a+b}")
            var title = a.toString()
            var priority = b.toString()
            if(b==0) priority = "Low"
            else if(b==1) priority = "Medium"
            else priority = "High"
            DataObject.setData(title, priority)
            GlobalScope.launch {
                database.dao().insertTask(Entity(0, title, priority))
            }
            GlobalScope.launch {
                Log.i("Mugdha", database.dao().getTask().toString())
            }
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        //write a ending of demo code --->

        save_button.setOnClickListener {
            if (create_title.text.toString().trim { it <= ' ' }
                    .isNotEmpty() && create_priority.text.toString().trim { it <= ' ' }
                    .isNotEmpty()) {
                var title = create_title.getText().toString()
                var priority = create_priority.getText().toString()
                DataObject.setData(title, priority)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority))
                }
                GlobalScope.launch {
                    Log.i("Mugdha", database.dao().getTask().toString())
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
//write a function to generate random words from dictionary
//fun getRandomWords(): String{
//    var z=arrayListOf<String>("apple","banana", "cherry", "date", "egg", "fish", "grape", "honey", "ice", "juice"
//        , "kiwi", "lemon", "mango", "orange", "pear", "pineapple", "plum", "raspberry", "strawberry", "tomato", "watermelon")
//    val random = (0..z.size).random()
//    return z[random]
//
//}
