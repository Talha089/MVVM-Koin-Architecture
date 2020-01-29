package com.example.mvvmroomtodo.di

import androidx.room.Room
import com.example.mvvmroomtodo.persistence.NOTE_DATABASE_NAME
import com.example.mvvmroomtodo.persistence.NoteDatabase
import com.example.mvvmroomtodo.repositories.NoteRepository
import com.example.mvvmroomtodo.repositories.NoteRepositoryImpl
import com.example.mvvmroomtodo.ui.NoteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 *  See https://start.insert-koin.io/#/quickstart/android
 **/
val appModule = module{

    // Provide NoteDatabase
    single{ Room.databaseBuilder(get(), NoteDatabase::class.java, NOTE_DATABASE_NAME).build() }

    // Provide NoteDao
    single{ get<NoteDatabase>().noteDao() }

    // Provide NoteRepository
    single<NoteRepository> { NoteRepositoryImpl(get()) }

    // Provide NoteViewModel
    viewModel { NoteViewModel(get()) }
}