package com.example.mvvmroomtodo.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmroomtodo.R
import com.example.mvvmroomtodo.models.Note
import com.example.mvvmroomtodo.util.DateUtils
import kotlinx.android.synthetic.main.layout_note_list_item.view.*

class NoteListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_note_list_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun getNotes():List<Note>{
        return differ.currentList
    }

    fun submitList(list: List<Note>){
        differ.submitList(list)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is NoteViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }


    class NoteViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Note) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            itemView.note_title.text = item.title
            itemView.note_timestamp.text = DateUtils.convertLongToStringDate(item.updated)
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Note)
    }
}