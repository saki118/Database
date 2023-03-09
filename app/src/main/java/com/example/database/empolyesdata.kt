package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*

class empolyesdata : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var empolyeelist : MutableList<info>
    lateinit var listview : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empolyesdata)

        empolyeelist = mutableListOf()
        listview = findViewById(R.id.listview1)
        ref = FirebaseDatabase.getInstance().getReference("Employees")

        ref.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot!!.exists()) {
                    empolyeelist.clear()

                    for (e in snapshot.children) {
                        val empolyee = e.getValue(info::class.java)
                        empolyeelist.add(empolyee!!)
                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}