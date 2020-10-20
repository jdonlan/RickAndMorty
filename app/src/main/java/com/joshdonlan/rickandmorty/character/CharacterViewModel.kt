package com.joshdonlan.rickandmorty.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.joshdonlan.rickandmorty.model.Character
import com.joshdonlan.rickandmorty.network.RickAndMortyApi
import kotlinx.coroutines.launch

class CharacterViewModel: ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>>
        get() = _characters

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character>
        get() = _character

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val response = RickAndMortyApi.retrofitService.getCharacters()
                _characters.value = response.results
            } catch (t: Throwable) {
                Log.e(CharacterViewModel::class.java.simpleName,"There was an error retrieving data: ${t.message}")
            }
        }
    }

    fun onCharacterClicked(character: Character) {
        Log.d(CharacterViewModel::class.java.simpleName, character.name)
        _character.value = character
    }

    fun clearCurrentCharacter() {
        _character.value = null
    }

}