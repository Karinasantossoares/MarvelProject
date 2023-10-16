package com.project.detail.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.project.detail.databinding.DetailProfileItemBinding
import com.project.detail.domain.model.ItemsModel


internal class DetailViewHolder(private val binding: DetailProfileItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: ItemsModel) {
        with(binding) {
            textDetail.text = result.name
        }
    }


}
