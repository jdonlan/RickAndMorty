package com.joshdonlan.rickandmorty.character.layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import com.joshdonlan.rickandmorty.R
import com.joshdonlan.rickandmorty.model.Character
import com.squareup.picasso.Picasso

class CharacterCard(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs), CharacterCardView {

    private val image by lazy { findViewById<ImageView>(R.id.character_image) }
    private val name by lazy { findViewById<TextView>(R.id.character_name) }
    private val location by lazy { findViewById<TextView>(R.id.character_location) }
    private val scrim by lazy { findViewById<View>(R.id.card_scrim) }

    init {
        View.inflate(context, R.layout.layout_character_card, this)
    }

    override fun showCharacter(character: Character?) {
        if (character == null) {
            visibility = View.GONE
        } else {
            val uri = character.image.toUri().buildUpon().scheme("https").build()
            Picasso.get()
                .load(uri)
                .placeholder(R.drawable.ic_character)
                .error(R.drawable.ic_character)
                .into(image)

            name.text = character.name
            location.text = character.location.name

            visibility = View.VISIBLE
        }
    }

    override fun getCharacterCardDismissView(): View {
        return scrim
    }

}