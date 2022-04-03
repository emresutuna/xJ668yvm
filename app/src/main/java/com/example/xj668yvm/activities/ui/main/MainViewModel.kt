package com.example.xj668yvm.activities.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.xj668yvm.activities.api.CharacterService
import com.example.xj668yvm.activities.models.Characters
import com.example.xj668yvm.activities.repository.CharacterServiceRepository
import com.example.xj668yvm.activities.repository.CharactersPagingSource
import java.util.concurrent.Flow

class MainViewModel(private val  service:CharacterServiceRepository) : ViewModel() {

    private lateinit var characterServiceRepository: CharacterServiceRepository

        val characters = Pager(PagingConfig(pageSize = 10)) {
            CharactersPagingSource(service)
        }.flow.cachedIn(viewModelScope)


}