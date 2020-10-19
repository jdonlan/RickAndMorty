package com.joshdonlan.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.joshdonlan.rickandmorty.character.CharacterActivity

class MainActivity : AppCompatActivity() {

    private val characterButton by lazy { findViewById<Button>(R.id.nav_character) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        characterButton.setOnClickListener {
            CharacterActivity.start(this)
        }

    }

}