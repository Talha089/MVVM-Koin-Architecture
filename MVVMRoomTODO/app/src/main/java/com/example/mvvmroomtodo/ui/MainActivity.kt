package com.example.mvvmroomtodo.ui

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import com.example.mvvmroomtodo.R
import com.example.mvvmroomtodo.models.Note
import com.example.mvvmroomtodo.models.SINGLE_NOTE_BUNDLE_KEY
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), IMainActivity {
    override fun onBackpressed() {
        viewModel.setExecuteSave(true) // save any notes being updated or created
        super.onBackPressed()
    }

    private val TAG: String = "AppDebug"

    // provide viewmodel instance using koin
    val viewModel: NoteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            (savedInstanceState[SINGLE_NOTE_BUNDLE_KEY] as Note?)?.let { note ->
                viewModel.setSingleNote(note)
            }
            (savedInstanceState[MODE_BUNDLE_KEY] as Int?)?.let { mode ->
                viewModel.setMode(mode)
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(SINGLE_NOTE_BUNDLE_KEY, viewModel.singleNote.value)
        viewModel.mode.value?.let { mode ->
            outState.putInt(MODE_BUNDLE_KEY, mode)
        }

    }

    override fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            inputMethodManager
                .hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
}






