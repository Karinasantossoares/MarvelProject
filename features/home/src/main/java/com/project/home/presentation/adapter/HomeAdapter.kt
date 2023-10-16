package com.project.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.project.home.databinding.CharacterItemBinding
import com.project.home.domain.model.CharacterModel
import com.project.home.domain.model.ResultsModel

internal class HomeAdapter : ListAdapter<ResultsModel, HomeViewHolder>(DiffUtil()) {

    var onItemClick: ((ResultsModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context))
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val results = getItem(position)
        holder.bind(results, onItemClick)
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ResultsModel>() {
        override fun areItemsTheSame(
            oldItem: ResultsModel,
            newItem: ResultsModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResultsModel,
            newItem: ResultsModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}
