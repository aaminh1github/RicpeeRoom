package com.example.notesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// click on key word and press Ctrl+Q to read more about them
@Database(entities = [Ricpee::class], version = 1, exportSchema = false)
abstract class RDatabase: RoomDatabase() {

    abstract fun RDao(): RDao

    companion object{
        @Volatile  // writes to this field are immediately visible to other threads
        private var INSTANCE: RDatabase? = null

        fun getDatabase(context: Context): RDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){  // protection from concurrent execution on multiple threads
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RDatabase::class.java,
                    "Ricpees_database"
                ).fallbackToDestructiveMigration()  // Destroys old database on version change
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
