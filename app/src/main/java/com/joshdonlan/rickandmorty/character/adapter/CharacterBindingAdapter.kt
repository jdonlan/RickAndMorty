package com.joshdonlan.rickandmorty.character.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.joshdonlan.rickandmorty.R
import com.squareup.picasso.Picasso

@BindingAdapter("characterImage")
fun fetchImage(view: ImageView, src: String?) {
    src?.let {
        val uri = src.toUri().buildUpon().scheme("https").build()
        Picasso.get()
            .load(uri)
            .placeholder(R.drawable.ic_character)
            .error(R.drawable.ic_character)
            .into(view)
    }
}
