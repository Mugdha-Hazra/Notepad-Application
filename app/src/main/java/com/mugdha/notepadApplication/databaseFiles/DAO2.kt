package com.mugdha.notepadApplication.databaseFiles

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAO2 {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetails(entity: Entity2)
    @Query("Select * from Profile")
    fun getProfileDetails(): List<Entity2>
}