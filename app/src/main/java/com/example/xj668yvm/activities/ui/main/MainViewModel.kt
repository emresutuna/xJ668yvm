package com.example.xj668yvm.activities.ui.main

import androidx.lifecycle.ViewModel
import com.example.xj668yvm.activities.repository.CharacterServiceRepository

class MainViewModel : ViewModel() {

    private lateinit var characterServiceRepository: CharacterServiceRepository

    init {
        callCharacters()
    }

    private fun callCharacters() {

    }
}