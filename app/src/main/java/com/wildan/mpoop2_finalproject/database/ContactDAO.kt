package com.wildan.mpoop2_finalproject.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface  ContactDAO {
    @Query("SELECT * FROM contact")
    fun loadTodos(): LiveData<List<Contact>>

    @Insert
    fun insertTodo(contact: Contact)

    @Update
    fun updateTodo(contact: Contact)

    @Delete
    fun deleteTodo(contact: Contact)
}