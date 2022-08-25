package com.mugdha.notepadApplication.activity

//import com.mugdha.notepadApplication.databaseFiles.Entity2
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mugdha.notepadApplication.R
import com.mugdha.notepadApplication.adapterFiles.DataObject
import com.mugdha.notepadApplication.databaseFiles.Entity2
import com.mugdha.notepadApplication.databaseFiles.myDatabase
import kotlinx.android.synthetic.main.activity_three.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class three : AppCompatActivity(){
    private lateinit var database: myDatabase
lateinit var imageView: ImageView
private val REQUEST_IMAGE_GALLERY = 11
private val REQUEST_IMAGE_CAMERA = 12
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)



//    database = Room.databaseBuilder(
//        applicationContext, myDatabase::class.java, "Profile"
//    ).build()


    database=myDatabase.getDatabase(application)
    //val save
    save.setOnClickListener {
        if (etname.text.toString().trim { it <= ' ' }
                .isNotEmpty() && etphone.text.toString().trim { it <= ' ' }
                .isNotEmpty()) {
            var name = etname.getText().toString()
            var phone = etphone.getText().toString()
            DataObject.setDataInTextView(name, phone)
            GlobalScope.launch {
                database.dao2().insertDetails(Entity2(0, name, phone))
            }
            GlobalScope.launch {
                Log.i("Mugdha", database.dao2().getProfileDetails().toString())
            }

            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()

            val pos = 0
            if (pos != -1) {
                Log.v("Mugdha", "********Position is *********$pos")
                val name = DataObject.getProfileData(pos).name
                val number = DataObject.getProfileData(pos).number
                etname.setText(name)
                etphone.setText(number)
                nametv.setText(name).toString()
                mobtv.setText(number).toString()
            }

            //set the data to the hint text view
            //DataObject.setDataInTextView(name, phone)
            //save the data to the database
            //database.dao2().insertDetails(Entity2(0, name, phone))
            //get the data from the database
            //Log.i("Mugdha", database.dao2().getProfileDetails().toString())
            //Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    imageView = findViewById(R.id.imagee)
    imageView.setOnClickListener {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Image Type!")
        builder.setMessage("Choose your option :D")
        builder.setPositiveButton("Gallery ;D") { dialog, _ ->
            dialog.dismiss()
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"

            startActivityForResult(intent, REQUEST_IMAGE_GALLERY)

        }
        builder.setNegativeButton("Camera :)") { dialog, which ->
            dialog.dismiss()
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePicture ->
                takePicture.resolveActivity(packageManager)?.also {
                    val permission = ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.CAMERA
                    )
                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(android.Manifest.permission.CAMERA),
                            1
                        )


                    } else {
                        startActivityForResult(takePicture, REQUEST_IMAGE_CAMERA)
                    }
                }
            }

        }
        var dialog: AlertDialog = builder.create()
        dialog.show()


    }
}

@Deprecated("Deprecated in Java")
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)


    if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
        imageView.setImageURI(data.data)
    }
    else
        if (requestCode == REQUEST_IMAGE_CAMERA && resultCode == Activity.RESULT_OK && data != null) {
            imageView.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
        else {
            Toast.makeText(this, "data not found!!", Toast.LENGTH_LONG).show()
        }





}
}