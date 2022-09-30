package com.example.assignment.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.assignment.data.Data
import com.example.assignment.databinding.ListItemBinding

class AspireAdapter : ListAdapter<Data, AspireAdapter.AspireViewHolder>(diffUtil) {

    class AspireViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun applyBinding(data: Data) {
            binding.apply {
                title.text = data.name
                idText.text = data.id.toString()
                imageView.load(data.url)
                imageFileName.text = data.image
                createdAt.text = data.created_at
                updatedAt.text = data.updated_at
            }
        }
    }

    companion object {
        val diffUtil: DiffUtil.ItemCallback<Data> = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AspireViewHolder {
        return AspireViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AspireViewHolder, position: Int) {
        holder.applyBinding(getItem(position))
    }
}