package com.example.noteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.DayBinding

class DayAdapter(val  dayList: ArrayList<day>):RecyclerView.Adapter<DayAdapter.DayHolder>() {
    class DayHolder(val binding: DayBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        val binding=DayBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DayHolder(binding)
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        val currentItem=dayList[position]
        holder.binding.weekname.text=currentItem.weekName
        holder.binding.daynumber.text=currentItem.dayName
        holder.binding.monthname.text=currentItem.monthName

    }
}