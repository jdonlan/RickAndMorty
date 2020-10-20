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

    private val _previous = MutableLiveData<Int>()
    val previous: LiveData<Int>
        get() = _previous

    private val _next = MutableLiveData<Int>()
    val next: LiveData<Int>
        get() = _next

    private var currentPage = 1

    fun getCharacters(page: Int = currentPage) {
        viewModelScope.launch {
            try {
                val response = RickAndMortyApi.retrofitService.getCharacters(page)
                _characters.value = response.results
                _previous.value = response.info.prev?.getLastQueryStringValue() ?: 0
                _next.value = response.info.next?.getLastQueryStringValue() ?: 0
                currentPage = page
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

    fun loadNext() {
        if (next.value != null) {
            getCharacters(next.value!!)
        }
    }

    fun loadPrevious() {
        if (next.value != null) {
            getCharacters(previous.value!!)
        }
    }

    fun String.getLastQueryStringValue(): Int {
        return this.substring(this.indexOf("=")+1,this.length).toInt()
    }

}