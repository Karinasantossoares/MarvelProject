package com.project.home.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.project.home.R
import com.project.home.databinding.CharacterItemBinding
import com.project.home.domain.model.ResultsModel
import com.project.ui.loadImage
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


internal class HomeViewHolder(private val binding: CharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: ResultsModel, onItemClick: ((ResultsModel) -> Unit)?) {
        with(binding) {
            nameCharacter.text = result.name
            imageCharacter.loadImage(result.thumbnail.path + "." + result.thumbnail.extension)
            root.setOnClickListener {
                onItemClick?.invoke(result)
            }
        }
    }


}
