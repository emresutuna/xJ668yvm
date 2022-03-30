package com.example.xj668yvm.activities.repository

import com.example.xj668yvm.activities.api.RetrofitInstance
import com.example.xj668yvm.activities.models.CharacterResponse
import retrofit2.Response

interface ICharacterServiceRepository {
    suspend fun getCharacter(page: Int): Response<CharacterResponse>
}

class CharacterServiceRepository : ICharacterServiceRepository {

    override suspend fun getCharacter(page: Int): Response<CharacterResponse> {
        return RetrofitInstance.apiCharacterService.getCharacters(page)
    }
}