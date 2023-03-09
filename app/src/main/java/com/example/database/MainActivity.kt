package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var editText3: EditText
    lateinit var editText4: EditText

    lateinit var btn1: Button
    lateinit var btn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.lastname)
        editText2 = findViewById(R.id.firstname)
        editText3 = findViewById(R.id.address)
        editText4 = findViewById(R.id.department)

        btn1 = findViewById(R.id.save)
        btn2 = findViewById(R.id.database)


        btn1.setOnClickListener {

            savedata()

        }
        btn2.setOnClickListener {

            val intent = Intent(this@MainActivity,empolyesdata::class.java)
            startActivity(intent)

        }



    }

    private fun savedata() {

        val firstname = editText1.text.toString().trim()
        val lastname  = editText2.text.toString().trim()
        val address   = editText3.text.toString().trim()
        val department= editText4.text.toString().trim()

        if (firstname.isEmpty())
        {
            editText1.error = "Please Enter Your Firstname"
            return
        }
        if (lastname.isEmpty())
        {
            editText1.error = "Please Enter Your Lastname"
            return
        }
        if (address.isEmpty())
        {
            editText1.error = "Please Enter Your Address"
            return
        }
        if (department.isEmpty())
        {
            editText1.error = "Please Enter Your Department"
            return
        }

        val mydatabase = FirebaseDatabase.getInstance().getReference("Employees")
        val empolyeeId = mydatabase.push().key
        val empolyee = info (empolyeeId.toString(),firstname , lastname , address , department)
        mydatabase.child(empolyeeId.toString()).setValue(empolyee).addOnCompleteListener {

            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()

        }


    }
}