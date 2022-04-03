package com.example.xj668yvm.activities.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xj668yvm.activities.ui.main.DetailViewModel

class DetailViewModelFactory(private val characterId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(characterId) as T

    }
}