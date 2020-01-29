package com.example.mvvmroomtodo.repositories

import com.example.mvvmroomtodo.models.Note

interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun deleteAllNotes()

    suspend fun getAllNotes(): List<Note>

}