package com.mugdha.notepadApplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mugdha.notepadApplication.R
import com.mugdha.notepadApplication.SwipeToDeleteCallBack
import com.mugdha.notepadApplication.adapterFiles.Adapter
import com.mugdha.notepadApplication.adapterFiles.DataObject
import com.mugdha.notepadApplication.databaseFiles.myDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var database: myDatabase
   // database=myDatabase.getDatabase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgbtn.setOnClickListener {
            val intent = Intent(this, three::class.java)
            startActivity(intent)
        }

        profile_button.setOnClickListener {
            val intent = Intent(this, two::class.java)
            startActivity(intent)
        }

        database=myDatabase.getDatabase(application)

//        database = Room.databaseBuilder(
//            applicationContext, myDatabase::class.java, "To_Do"
//        ).build()
        val recyclerView=findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recycler_view.adapter = Adapter(DataObject.getAllData())
        }

        val swipeToDeleteCallBack=object:SwipeToDeleteCallBack()
        {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
            {
                val pos = viewHolder.adapterPosition
                Log.v("Mugdha","pos main $pos")
                if (pos != -1)
                {
                    val title = viewHolder.itemView.title
                    DataObject.deleteData(pos)
                    GlobalScope.launch {
                        database.dao().deleteTask(title.text.toString())
                    }
                }
                setRecycler()
            }
        }
        val itemTouchHelper=ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        database=myDatabase.getDatabase(application)

//        database = Room.databaseBuilder(
//            applicationContext, myDatabase::class.java, "To_Do"
//        ).build()

        //for using profiler  --> demo code started
        //i=0
//        for (i in 1..3) {
//            println(i)
//            val intent = Intent(this, CreateCard::class.java)
//            startActivity(intent)
//        }

//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        // demo code ended



        add.setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }
        deleteAll.setOnClickListener {
            DataObject.deleteAll()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
            setRecycler()
        }
        setRecycler()
    }

    fun setRecycler() {
        recycler_view.adapter = Adapter(DataObject.getAllData())
        recycler_view.layoutManager = LinearLayoutManager(this)
    }
}