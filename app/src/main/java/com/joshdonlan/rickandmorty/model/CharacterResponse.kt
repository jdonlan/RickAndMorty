package com.joshdonlan.rickandmorty.model

data class CharacterResponse (
    val info: ResponseInfo,
    val results: List<Character>
)