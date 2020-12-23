package com.wildan.mpoop2_finalproject.database

import androidx.room.*
import com.wildan.mpoop2_finalproject.model.Contact

@Dao
interface ContactDao {

    @Insert
    fun insert(contact: Contact)

    @Update
    fun update(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Query("SELECT * FROM contacts")
    fun getAll() : List<Contact>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getById(id: Int) : List<Contact>
}