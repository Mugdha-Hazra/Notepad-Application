package com.mugdha.notepadApplication.databaseFiles

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class,Entity2::class], version = 1, exportSchema = false)
abstract class myDatabase : RoomDatabase() {
    abstract fun dao(): DAO
   abstract fun dao2(): DAO2

    companion object {
        private var INSTANCE: myDatabase? = null
        fun getDatabase(context: Context): myDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    myDatabase::class.java,
                    "To_Do",
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }}

