package com.example.mvvmroomtodo.repositories

import com.example.mvvmroomtodo.models.Note
import com.example.mvvmroomtodo.persistence.NoteDao

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository{

    private val TAG: String = "AppDebug"

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }

    override suspend fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes()
    }

}

