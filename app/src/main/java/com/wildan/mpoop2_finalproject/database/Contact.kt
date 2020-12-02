package com.wildan.mpoop2_finalproject.database

import androidx.room.Entity
import androidx.room.PrimaryKey
// test
@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var contact: String
)