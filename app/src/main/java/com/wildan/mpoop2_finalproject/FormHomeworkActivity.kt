package com.wildan.mpoop2_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.wildan.mpoop2_finalproject.model.Homework
import kotlinx.android.synthetic.main.activity_form_homework.*

class FormHomeworkActivity : AppCompatActivity() {
    var homework : Homework? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_homework)

        val data = intent.getSerializableExtra("homework")
        var edit = true

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("homeworks")

        if (data != null) {
            homework = data as Homework
            etHomeworkNameEdit.setText(homework?.title)
            etHomeworkDescriptionEdit.setText(homework?.description)

            btActForm.setText("Edit")
        } else {
            btActForm.setText("Tambah")
            edit = false
        }

        btActForm.setOnClickListener {
            if (edit) {
                val changeData = HashMap<String, Any>()
                changeData.put("title", etHomeworkNameEdit.text.toString())
                changeData.put("description", etHomeworkDescriptionEdit.text.toString())

                myRef.child(homework?.key.toString()).updateChildren(changeData)
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                val key = myRef.push().key

                val newNote = Homework()
                newNote.title = etHomeworkNameEdit.text.toString()
                newNote.description = etHomeworkDescriptionEdit.text.toString()

                myRef.child(key.toString()).setValue(newNote)
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}