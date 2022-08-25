package com.mugdha.notepadApplication.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mugdha.notepadApplication.R
import kotlinx.android.synthetic.main.activity_two.*

class two : AppCompatActivity() {
//    private lateinit var database: myDatabase
    lateinit var userTV: TextView
    lateinit var logoutBtn: Button
    var PREFS_KEY = "prefs"
    var EMAIL_KEY = "email"
    var email = ""

    // on below line we are creating a variable for shared preferences.
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
       // database=myDatabase.getDatabase(application)

//        val pos = 0
//        if (pos != -1) {
//            Log.v("Mugdha", "********Position is *********$pos")
//            val name = DataObject.getProfileData(pos).name
//            val number = DataObject.getProfileData(pos).number
//            etname.setText(name)
//            etphone.setText(number)
//            nametv.setText(name).toString()
//            mobtv.setText(number).toString()
//        }

        // on below line we are initializing
        // our user text view and logout button.
        userTV = findViewById(R.id.idTVUserName)
        logoutBtn = findViewById(R.id.idBtnLogOut)

        // on below line we are initializing our shared preferences variable.
        sharedPreferences = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)

        // on below line we are getting the data from
        // email and setting it in email variable.
        email = sharedPreferences.getString(EMAIL_KEY, null)!!

        // on below line we are setting a text to user text view.
        userTV.setText("Welcome \n$email")

        //onclick listener for getstarted button.
        idBtn.setOnClickListener {
            // on below line we are clearing the shared preferences.
//            sharedPreferences.edit().clear().apply()
            // on below line we are starting the main activity.
            startActivity(Intent(this, MainActivity::class.java))
            // on below line we are finishing the current activity.
            finish()
        }

        // on below line we are setting a click listener to logout button.
        // on below line we are adding
        // on click listener for our logout button.
        logoutBtn.setOnClickListener {

            // on below line we are creating a variable for
            // editor of shared preferences and initializing it.
            val editor: SharedPreferences.Editor = sharedPreferences.edit()

            // on below line we are clearing our editor.
            editor.clear()

            // on below line we are applying changes which are cleared.
            editor.apply()

            // on below line we are opening our mainactivity by calling intent
            val i = Intent(this@two, one::class.java)

            // on below line we are simply starting
            // our activity to start main activity
            startActivity(i)

            // on below line we are calling
            // finish to close our main activity 2.
            finish()
        }
    }
}