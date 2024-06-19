package com.example.noteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.TypeBinding

class TypeAdapter(private val novList: ArrayList<Nov>) : RecyclerView.Adapter<TypeAdapter.TypeHolder>() {

    class TypeHolder(val binding: TypeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeHolder {
        val binding = TypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TypeHolder(binding)
    }

    override fun getItemCount(): Int {
        return novList.size
    }

    override fun onBindViewHolder(holder: TypeHolder, position: Int) {
        val currentItem = novList[position]
        holder.binding.Nov.text = currentItem.nametype
    }
}
