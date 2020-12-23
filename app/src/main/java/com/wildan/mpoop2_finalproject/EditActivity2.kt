package com.wildan.mpoop2_finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.wildan.mpoop2_finalproject.database.ContactDao
import com.wildan.mpoop2_finalproject.database.ContactRoomDatabase
import com.wildan.mpoop2_finalproject.model.Contact
import kotlinx.android.synthetic.main.activity_edit2.*

class EditActivity2 : AppCompatActivity() {

    val EDIT_CONTACT_EXTRA = "edit_contact_extra"
    private lateinit var contact: Contact
    private var isUpdate = false
    private lateinit var database: ContactRoomDatabase
    private lateinit var dao: ContactDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit2)

        database = ContactRoomDatabase.getDatabase(applicationContext)
        dao = database.getContactDao()

        if (intent.getParcelableExtra<Contact>(EDIT_CONTACT_EXTRA) != null){
            button_delete2.visibility = View.VISIBLE
            isUpdate = true
            contact = intent.getParcelableExtra(EDIT_CONTACT_EXTRA)
            edit_text_name.setText(contact.name)
            edit_text_operator.setText(contact.operator)
            edit_text_nomor.setText(contact.nomor)

            edit_text_name.setSelection(contact.name.length)

        }

        button_save2.setOnClickListener {
            val name = edit_text_name.text.toString()
            val operator = edit_text_operator.text.toString()
            val nomor = edit_text_nomor.text.toString()

            if (name.isEmpty() && operator.isEmpty() && nomor.isEmpty()){
                Toast.makeText(applicationContext, "Contact cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else{
                if (isUpdate){
                    saveContact(Contact(id = contact.id, name = name, operator = operator, nomor = nomor))
                }
                else{
                    saveContact(Contact(name = name, operator = operator, nomor = nomor))
                }
            }

            finish()
        }

        button_delete2.setOnClickListener {
            deleteContact(contact)
            finish()
        }

    }

    private fun saveContact(contact: Contact){

        if (dao.getById(contact.id).isEmpty()){

            dao.insert(contact)
        }
        else{

            dao.update(contact)
        }

        Toast.makeText(applicationContext, "Contact saved", Toast.LENGTH_SHORT).show()

    }

    private fun deleteContact(contact: Contact){
        dao.delete(contact)
        Toast.makeText(applicationContext, "Contact removed", Toast.LENGTH_SHORT).show()
    }
}