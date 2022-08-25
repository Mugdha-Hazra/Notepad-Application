package com.mugdha.notepadApplication.databaseFiles

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "To_Do")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: String,
)
//create entity profile details for name and mobile number
@Entity(tableName = "Profile")
data class Entity2(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var mobile: String,
)
