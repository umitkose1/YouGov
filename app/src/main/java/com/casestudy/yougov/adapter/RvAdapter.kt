package com.casestudy.yougov.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casestudy.yougov.databinding.LineItemBinding
import com.casestudy.yougov.model.TimerItemsModel

class RvAdapter(
    var savedList: List<TimerItemsModel>,
    private val clickListener: (TimerItemsModel) -> Unit

) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LineItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LineItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(savedList[position]) {
                binding.tvDateName.text = this.hours + ":" + this.minutes + ":" + this.seconds
            }
        }
        holder.itemView.setOnClickListener {
            clickListener(savedList[position])
        }
    }

    override fun getItemCount(): Int {
        return savedList.size
    }
}