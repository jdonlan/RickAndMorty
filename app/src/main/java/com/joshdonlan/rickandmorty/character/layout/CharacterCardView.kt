package com.joshdonlan.rickandmorty.character.layout

import android.view.View
import com.joshdonlan.rickandmorty.model.Character

interface CharacterCardView {
    fun showCharacter(character: Character?)
    fun getCharacterCardDismissView(): View
}