package com.example.androidprac.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidprac.databinding.ListItemRandomBinding

class RandomAdapter : ListAdapter<String, RandomAdapter.RandomViewHolder>(diffUtilCallBack) {
    companion object {
        private val diffUtilCallBack = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

        }
    }

    class RandomViewHolder(
        private val binding: ListItemRandomBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            str: String,
            index: Int
        ) {
            binding.numberingTextView.text = (index + 1).toString()
            binding.detailTextView.text = str
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomViewHolder {
        return RandomViewHolder(
            binding = ListItemRandomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RandomViewHolder, position: Int) {
        holder.bind(
            str = this.getItem(position),
            index = position
        )
    }
}