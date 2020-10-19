package com.joshdonlan.rickandmorty.model

import java.util.Date

data class Character (
    val id: Int,
    val name: String,
    val species: String,
    val type: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: Date
)