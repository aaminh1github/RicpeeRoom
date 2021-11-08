package com.example.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRicpee(ricpee: Ricpee)

    @Query("SELECT * FROM RicpeesTable ORDER BY id ASC")
    fun getRicpees(): LiveData<List<Ricpee>>

    @Update
    suspend fun updateRicpee(ricpee: Ricpee)

    @Delete
    suspend fun deleteRicpee(ricpee: Ricpee)

}
