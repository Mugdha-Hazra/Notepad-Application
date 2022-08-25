package com.mugdha.notepadApplication.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mugdha.notepadApplication.R
import kotlinx.android.synthetic.main.activity_one.*

class one : AppCompatActivity() {
    lateinit var emailEdt: EditText
    lateinit var pwdEdt: EditText
    lateinit var loginBtn: Button
    lateinit var sharedPreferences: SharedPreferences
    private var PREFS_KEY = "prefs"
    private var EMAIL_KEY = "email"
    private var PWD_KEY = "pwd"
    private var email = ""
    private var pwd = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        emailEdt = findViewById(R.id.idEdtEmail)
        pwdEdt = findViewById(R.id.idEdtPassword)
        loginBtn = findViewById(R.id.idBtnLogin)
        sharedPreferences = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
        email = sharedPreferences.getString(EMAIL_KEY, "").toString()
        pwd = sharedPreferences.getString(PWD_KEY, "").toString()
        loginBtn.setOnClickListener {
            if (TextUtils.isEmpty(emailEdt.text.toString()) || TextUtils.isEmpty(pwdEdt.text.toString())) {
                if (TextUtils.isEmpty(emailEdt.text.toString())) {
                    Toast.makeText(this, "Please Enter  Email and Password", Toast.LENGTH_SHORT)
                        .show();
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEdt.text.toString())
                        .matches()
                ) {
                    Toast.makeText(this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pwdEdt.text.toString())) {
                    Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }

            } else if (TextUtils.isEmpty(pwdEdt.text.toString())) {
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            }
            else {
                if(switch1.isChecked){
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString(EMAIL_KEY, emailEdt.text.toString())
                    editor.putString(PWD_KEY, pwdEdt.text.toString())
                    editor.apply()
                    val i = Intent(this@one, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(EMAIL_KEY, emailEdt.text.toString())
                editor.putString(PWD_KEY, pwdEdt.text.toString())
                editor.apply()
                val i = Intent(this@one, two::class.java)
                startActivity(i)
                finish()
            }}
        }
    override fun onStart() {
        super.onStart()
        if (!email.equals("") && !pwd.equals("")) {
            val i = Intent(this@one, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}