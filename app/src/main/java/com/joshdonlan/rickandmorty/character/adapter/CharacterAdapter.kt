package com.joshdonlan.rickandmorty.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joshdonlan.rickandmorty.databinding.ItemCharacterBinding
import com.joshdonlan.rickandmorty.model.Character

class CharacterAdapter(private val clickListener: CharacterListener): ListAdapter<Character, CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

}

class CharacterDiffCallback: DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name && oldItem.species == newItem.species && oldItem.image == newItem.image
    }
}

class CharacterViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: CharacterListener, item: Character) {
        binding.character = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): CharacterViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)

            return CharacterViewHolder(binding)
        }
    }
}

class CharacterListener(val clickListener: (character: Character) -> Unit) {
    fun onClick(character: Character) = clickListener(character)
}