package com.example.xj668yvm.activities.repository

import com.example.xj668yvm.activities.api.RetrofitInstance
import com.example.xj668yvm.activities.models.CharacterResponse
import com.google.gson.JsonObject
import retrofit2.Response

interface ICharacterServiceRepository {
    suspend fun getCharacter(page: Int): Response<JsonObject>
    suspend fun getSingleCharacter(id: Int): Response<JsonObject>
}

class CharacterServiceRepository : ICharacterServiceRepository {

    override suspend fun getCharacter(page: Int): Response<JsonObject> {
        return RetrofitInstance.apiCharacterService.getCharacters(page)
    }

    override suspend fun getSingleCharacter(id: Int): Response<JsonObject> {
        return RetrofitInstance.apiCharacterService.getSingleCharacter(id)

    }
}