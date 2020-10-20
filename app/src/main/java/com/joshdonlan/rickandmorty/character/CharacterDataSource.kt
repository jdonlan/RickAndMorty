package com.joshdonlan.rickandmorty.character

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import com.joshdonlan.rickandmorty.model.Character
import com.joshdonlan.rickandmorty.network.RickAndMortyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDataSource(private val viewModel: CharacterViewModel): PageKeyedDataSource<Int, Character>() {

    val dataService by lazy { RickAndMortyApi.retrofitService }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = dataService.getCharacters(1)
                callback.onResult(response.results, null, 2)
            } catch (t: Throwable) {
                Log.e(CharacterViewModel::class.java.simpleName,"There was an error retrieving data: ${t.message}")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            try {
                val page = params.key
                val response = dataService.getCharacters(page)
                callback.onResult(response.results, (page+1))
            } catch (t: Throwable) {
                Log.e(CharacterViewModel::class.java.simpleName,"There was an error retrieving data: ${t.message}")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        //Not implemented
    }
}