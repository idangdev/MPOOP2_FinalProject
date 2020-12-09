package com.wildan.mpoop2_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.wildan.mpoop2_finalproject.databinding.ActivityMainBinding

//test
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.contactBtn.setOnClickListener{
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }

        binding.noteBtn.setOnClickListener{
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }

    }
}