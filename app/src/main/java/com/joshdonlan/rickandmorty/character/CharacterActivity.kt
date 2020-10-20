package com.joshdonlan.rickandmorty.character

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joshdonlan.rickandmorty.R
import com.joshdonlan.rickandmorty.character.adapter.CharacterAdapter
import com.joshdonlan.rickandmorty.character.adapter.CharacterListener
import com.joshdonlan.rickandmorty.character.layout.CharacterCard
import com.joshdonlan.rickandmorty.character.layout.CharacterCardView
import com.joshdonlan.rickandmorty.databinding.ActivityCharacterBinding
import com.joshdonlan.rickandmorty.model.Character

class CharacterActivity : AppCompatActivity(), CharacterCardView {

    private val viewModel: CharacterViewModel by lazy { ViewModelProvider(this, CharacterViewModelFactory()).get(CharacterViewModel::class.java) }
    private val characterAdapter by lazy { initCharacterAdapter() }
    private val characterCard by lazy { findViewById<CharacterCard>(R.id.character_card) }
    private val nextPage by lazy { findViewById<Button>(R.id.button_next) }
    private val prevPage by lazy { findViewById<Button>(R.id.button_prev) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityCharacterBinding>(this, R.layout.activity_character)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.characterList.adapter = characterAdapter

        viewModel.characters.observe(this, Observer {
            updateCharacters(it)
        })

        viewModel.character.observe(this, Observer {
            showCharacter(it)
        })

        viewModel.previous.observe(this, Observer {
            prevPage.isEnabled = it != 0
        })

        viewModel.next.observe(this, Observer {
            nextPage.isEnabled = it != 0
        })

        getCharacterCardDismissView().setOnClickListener {
            viewModel.clearCurrentCharacter()
        }

        prevPage.setOnClickListener {
            viewModel.loadPrevious()
        }

        nextPage.setOnClickListener {
            viewModel.loadNext()
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getCharacters()
    }

    override fun getCharacterCardDismissView(): View {
        return characterCard.getCharacterCardDismissView()
    }

    override fun showCharacter(character: Character?) {
        characterCard.showCharacter(character)
    }

    private fun initCharacterAdapter(): CharacterAdapter {
        val characterListener = CharacterListener { character -> viewModel.onCharacterClicked(character) }
        return CharacterAdapter(characterListener)
    }

    private fun updateCharacters(characters: List<Character>) {
        characterAdapter.submitList(characters)
    }

    companion object {
        fun start(caller:Activity) {
            val intent = Intent(caller, CharacterActivity::class.java)
            caller.startActivity(intent)
        }
    }

}