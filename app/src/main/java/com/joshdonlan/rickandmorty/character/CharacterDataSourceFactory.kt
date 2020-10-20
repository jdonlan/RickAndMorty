package com.joshdonlan.rickandmorty.character

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.joshdonlan.rickandmorty.model.Character

class CharacterDataSourceFactory(private val viewModel: CharacterViewModel): DataSource.Factory<Int, Character>() {

    private val mutableLiveData = MutableLiveData<CharacterDataSource>()

    fun getMutableLiveData(): MutableLiveData<CharacterDataSource> {
        return mutableLiveData
    }

    override fun create(): DataSource<Int, Character> {
        return CharacterDataSource(viewModel)
    }

}