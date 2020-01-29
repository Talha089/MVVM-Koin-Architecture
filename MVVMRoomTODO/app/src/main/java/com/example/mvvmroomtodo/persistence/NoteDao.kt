package com.example.mvvmroomtodo.persistence

import androidx.room.*
import com.example.mvvmroomtodo.models.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Note>

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

}