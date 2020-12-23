package com.wildan.mpoop2_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wildan.mpoop2_finalproject.database.ContactRoomDatabase
import com.wildan.mpoop2_finalproject.model.Contact
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        getContactsData()

        floatingActionButton2.setOnClickListener {
            startActivity(Intent(this, EditActivity2::class.java))
        }
    }

    private fun getContactsData(){
        val database = ContactRoomDatabase.getDatabase(applicationContext)
        val dao = database.getContactDao()
        val listItems = arrayListOf<Contact>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
        if (listItems.isNotEmpty()){
            text_view_note_empty2.visibility = View.GONE
        }
        else{
            text_view_note_empty2.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView(listItems: ArrayList<Contact>){
        recycler_view_main2.apply {
            adapter = ContactAdapter(listItems, object : ContactAdapter.ContactListener{
                override fun OnItemClicked(contact: Contact) {
                    val intent = Intent(this@ContactActivity, EditActivity2::class.java)
                    intent.putExtra(EditActivity2().EDIT_CONTACT_EXTRA, contact)
                    startActivity(intent)
                }
            })

            layoutManager = LinearLayoutManager(this@ContactActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        getContactsData()
    }
}