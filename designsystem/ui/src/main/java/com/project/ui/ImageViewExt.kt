package com.project.ui

import android.widget.ImageView
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?) {
    if (url?.isEmpty() == true) {
        setImageResource(R.drawable.marvel_place)
        return
    }
    Picasso.get()
        .load(url)
        .networkPolicy(NetworkPolicy.OFFLINE)
        .error(R.drawable.marvel_place)
        .into(this@loadImage)

}