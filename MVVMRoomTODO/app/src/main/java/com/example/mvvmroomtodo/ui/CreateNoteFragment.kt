package com.example.mvvmroomtodo.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mvvmroomtodo.R
import com.example.mvvmroomtodo.models.Note
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.android.synthetic.main.layout_create_note_toolbar.*
import kotlinx.android.synthetic.main.layout_note_list_item.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.lang.ClassCastException


/**
 * Class for creating new notes
 */
class CreateNoteFragment : Fragment() {

    private val TAG: String = "AppDebug"

    //"SharedViewModel": reuse existing viewmodel from hosting activity
    val viewModel: NoteViewModel by sharedViewModel()

    lateinit var iMainActivity: IMainActivity

    private val note: Note by lazy {
        Note(
            title = getString(R.string.default_note_title)
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        create_note_toolbar_check.setOnClickListener {
            iMainActivity.hideSoftKeyboard()
            iMainActivity.onBackpressed()
        }

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.executeSave.observe(viewLifecycleOwner, Observer { executeSave ->
            executeSave?.let {
                if (it) {
                    saveNewNote()
                    viewModel.setExecuteSave(false)  //reset

                }
            }
        })
    }

    fun saveNewNote() {
        create_note_title?.let { title ->
            note.title = title.text.toString()
        }
        note.content = create_note_content.text.toString()

        if (validateFields()) {
            viewModel.insertNote(note)
        }
    }

    private fun validateFields(): Boolean {
        when {
            note.title.isBlank() -> {
                return false
            }
            note.title.equals(getString(R.string.default_note_title)) -> {
                return false
            }
        }
        return true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            iMainActivity = context as IMainActivity
        } catch (e: ClassCastException) {
            Log.e(TAG, "$context must implement IMainActivity")

        }
    }


    override fun onPause() {
        super.onPause()
        viewModel.setSingleNote(note)
    }


}