package com.example.notesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RicpeesTable")
data class Ricpee(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val RText: String,
val insText :String,
        val AuthText :String)
