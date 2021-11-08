package com.example.notesapp.data

import androidx.lifecycle.LiveData

class RicpeeRepository(private val noteDao: RDao) {

    val getRicpees: LiveData<List<Ricpee>> = noteDao.getRicpees()

    suspend fun addRicpee(ricpee: Ricpee){
        noteDao.addRicpee(ricpee)
    }

    suspend fun updateRicpee(ricpee: Ricpee){
        noteDao.updateRicpee(ricpee)
    }

    suspend fun deleteRicpee(ricpee: Ricpee){
        noteDao.deleteRicpee(ricpee)
    }

}
