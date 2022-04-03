package com.example.xj668yvm.activities.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xj668yvm.activities.api.CharacterService
import com.example.xj668yvm.activities.repository.CharacterServiceRepository
import com.example.xj668yvm.activities.ui.main.MainViewModel


class MainViewModelFactory(private val service: CharacterServiceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(service) as T

    }
}