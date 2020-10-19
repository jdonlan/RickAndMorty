package com.joshdonlan.rickandmorty.character

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joshdonlan.rickandmorty.R
import com.joshdonlan.rickandmorty.character.adapter.CharacterAdapter
import com.joshdonlan.rickandmorty.character.adapter.CharacterListener
import com.joshdonlan.rickandmorty.databinding.ActivityCharacterBindingImpl

class CharacterActivity : AppCompatActivity() {

    private val viewModel: CharacterViewModel by lazy { ViewModelProvider(this, CharacterViewModelFactory()).get(CharacterViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCharacterBindingImpl.inflate(layoutInflater)

        val characterListener = CharacterListener { character -> viewModel.onCharacterClicked(character) }
        val characterAdapter = CharacterAdapter(characterListener)
        binding.characterList.adapter = characterAdapter

        viewModel.characters.observe(this, Observer {
            characterAdapter.submitList(it)
        })

        setContentView(R.layout.activity_character)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCharacters()
    }

    companion object {
        fun start(caller:Activity) {
            val intent = Intent(caller, CharacterActivity::class.java)
            caller.startActivity(intent)
        }
    }

}