package com.example.mvvmroomtodo.ui

import com.example.mvvmroomtodo.models.Note

interface INoteViewModel {

    //Note List Fragment
    fun insertNote(note: Note)

    fun updateNote(note: Note)

    fun deleteNote(note: Note)

    fun deleteAllNotes()

    fun retrieveAllNotes()

    fun selectNote(note: Note)

    // NoteDetailFragment
}