package com.joshdonlan.rickandmorty.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.joshdonlan.rickandmorty.model.Character
import com.joshdonlan.rickandmorty.model.CharacterResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Date

interface RickAndMortyService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Character
}

object RickAndMortyApi {

    private const val baseURL = "https://rickandmortyapi.com/api/"

    private val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(RickAndMortyClient.getClient())
        .baseUrl(baseURL)
        .build()

    val retrofitService: RickAndMortyService by lazy {
        retrofit.create(RickAndMortyService::class.java)
    }
}