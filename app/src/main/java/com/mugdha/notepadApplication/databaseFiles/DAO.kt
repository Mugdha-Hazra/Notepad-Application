package com.mugdha.notepadApplication.databaseFiles

import androidx.room.*
import com.mugdha.notepadApplication.adapterFiles.CardInfo

@Dao

interface DAO {
    @Insert
    fun insertTask(entity: Entity)

    @Update
    fun updateTask(entity: Entity)

    @Delete
    fun deleteTask(entity: Entity)

    @Query("Delete from to_do")
    fun deleteAll()

    @Query("Select * from to_do")
    fun getTask(): List<CardInfo>

    @Query("Delete from to_do where title =:title")
    fun deleteTask(title:String)
}