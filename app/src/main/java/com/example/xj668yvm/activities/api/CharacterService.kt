package com.example.xj668yvm.activities.api

import com.example.xj668yvm.activities.models.CharacterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET

interface CharacterService {
    @GET("api/character")
    suspend fun getCharacters(@Field("page") page: Int): Response<CharacterResponse>
}