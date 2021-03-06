package com.wildan.mpoop2_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.wildan.mpoop2_finalproject.databinding.ActivityMainBinding

//test
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.contactBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, ContactActivity::class.java)
            startActivity(intent)
        }

        binding.noteBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, NoteActivity::class.java)
            startActivity(intent)
        }

        binding.btnTentang.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intent)
        }

        binding.btnHomework.setOnClickListener {
            val intent = Intent(this@MainActivity, HomeworkActivity::class.java)
            startActivity(intent)
        }

    }
}