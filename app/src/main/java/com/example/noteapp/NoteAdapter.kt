package com.example.noteapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NotepageBinding

class NoteAdapter(private val noteList: ArrayList<Note>): RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    class NoteHolder(val binding: NotepageBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding = NotepageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = noteList[position]
        holder.binding.nateheadname.text = note.headName
        holder.binding.noteabout.text = note.text

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, NotePage::class.java)
            intent.putExtra("title", note.headName)
            intent.putExtra("details", note.text)
            holder.itemView.context.startActivity(intent)
            holder.itemView.setOnClickListener {
                val intent= Intent(holder.itemView.context,NotePage::class.java)
                intent.putExtra("Note",noteList.get(position))
                holder.itemView.context.startActivity(intent)
            }
        }
    }
}
