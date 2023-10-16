package com.project.detail.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.project.detail.databinding.DetailProfileItemBinding
import com.project.detail.domain.model.ItemsModel

internal class DetailAdapter : ListAdapter<ItemsModel, DetailViewHolder>(DiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = DetailProfileItemBinding.inflate(LayoutInflater.from(parent.context))
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val results = getItem(position)
        holder.bind(results)
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ItemsModel>() {
        override fun areItemsTheSame(
            oldItem: ItemsModel,
            newItem: ItemsModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: ItemsModel,
            newItem: ItemsModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}
