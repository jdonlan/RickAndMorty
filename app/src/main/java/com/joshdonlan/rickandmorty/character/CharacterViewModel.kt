package com.joshdonlan.rickandmorty.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.joshdonlan.rickandmorty.model.Character
import java.util.concurrent.Executors

class CharacterViewModel: ViewModel() {

    private val characterDataSourceFactory = CharacterDataSourceFactory(this)

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(10)
        .setPageSize(20)
        .setPrefetchDistance(4)
        .build()

    private val executor = Executors.newFixedThreadPool(5)

    private val pagedListLiveData = LivePagedListBuilder<Int, Character>(characterDataSourceFactory, config)
        .setFetchExecutor(executor)
        .build()

    val characters: LiveData<PagedList<Character>>
        get() = pagedListLiveData

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character>
        get() = _character

    private val _previous = MutableLiveData<Int>()
    val previous: LiveData<Int>
        get() = _previous

    private val _next = MutableLiveData<Int>()
    val next: LiveData<Int>
        get() = _next

    fun onCharacterClicked(character: Character) {
        Log.d(CharacterViewModel::class.java.simpleName, character.name)
        _character.value = character
    }

    fun clearCurrentCharacter() {
        _character.value = null
    }

}