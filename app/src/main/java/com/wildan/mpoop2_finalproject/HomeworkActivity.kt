package com.wildan.mpoop2_finalproject

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*
import com.wildan.mpoop2_finalproject.model.Homework
import kotlinx.android.synthetic.main.activity_homework.*

class HomeworkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework)

        val database = FirebaseDatabase.getInstance()

        var  myRef : DatabaseReference? = database.getReference("homeworks")

        // Read Data
        myRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // looping ketika mengambil data
                // merah ? coba tambahkan ()
                val dataArray = ArrayList<Homework>()
                for (i in dataSnapshot.children){
                    val data = i.getValue(Homework::class.java)
                    data?.key = i.key
                    data?.let { dataArray.add(it) }
                }
                rvListNotes.adapter = HomeworkAdapter(dataArray, object : HomeworkAdapter.OnClick {
                    override fun edit(homework: Homework?) {
                        val intent = Intent(this@HomeworkActivity, FormHomeworkActivity::class.java)
                        intent.putExtra("homework", homework)
                        startActivity(intent)
                    }

                    override fun delete(key: String?) {
                        AlertDialog.Builder(this@HomeworkActivity).apply {
                            setTitle("Hapus ?")
                            setPositiveButton("Ya") { dialogInterface: DialogInterface, i: Int ->
                                myRef?.child(key.toString())?.removeValue()
//                                Toast.makeText(this@MainActivity, key, Toast.LENGTH_SHORT).show()
                            }
                            setNegativeButton("Tidak", { dialogInterface: DialogInterface, i: Int -> })
                        }.show()
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException())
            }
        })

        btAddNote.setOnClickListener {
            startActivity(Intent(this@HomeworkActivity, FormHomeworkActivity::class.java))
        }
    }
}