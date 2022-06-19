package com.akyuzg.rapsodomotiontracker.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akyuzg.rapsodomotiontracker.databinding.RecordItemBinding
import com.akyuzg.rapsodomotiontracker.domain.model.Record

class RecordListAdapter(
    private val onItemClicked: (Record) -> Unit
): ListAdapter<Record, RecordListAdapter.RecordViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Record>() {
            override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val viewHolder = RecordViewHolder(
            RecordItemBinding.inflate(LayoutInflater.from( parent.context), parent, false)
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class RecordViewHolder(
        private var binding: RecordItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(record: Record) {
            binding.titleTextView.text = record.name
            binding.descriptionTextView.text = record.description
        }
    }


}