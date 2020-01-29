package com.example.mvvmroomtodo.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmroomtodo.models.Note

const val NOTE_DATABASE_NAME = "notes_database"

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase(){

    abstract fun noteDao(): NoteDao
}