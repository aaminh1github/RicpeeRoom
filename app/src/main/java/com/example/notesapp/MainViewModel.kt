package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.data.Ricpee
import com.example.notesapp.data.RDatabase
import com.example.notesapp.data.RicpeeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository: RicpeeRepository
    private val ricpees: LiveData<List<Ricpee>>

    init {
        val noteDao = RDatabase.getDatabase(application).RDao()
        repository = RicpeeRepository(noteDao)
        ricpees = repository.getRicpees
    }

    fun getRicpees(): LiveData<List<Ricpee>>{
        return ricpees
    }

    fun addRicpee(titleText: String,insText:String,authText:String){
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            repository.addRicpee(Ricpee(0, titleText,insText,authText))
        }
    }

    fun editRicpee(noteID: Int, noteText: String,insText: String,authText: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateRicpee(Ricpee(noteID,noteText,insText,authText))
        }
    }

    fun deleteRicpee(noteID: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteRicpee(Ricpee(noteID,"","",""))
        }
    }
}