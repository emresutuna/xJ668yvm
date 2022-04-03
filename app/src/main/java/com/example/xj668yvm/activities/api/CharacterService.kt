package com.example.xj668yvm.activities.api

import com.example.xj668yvm.activities.models.CharacterResponse
import com.example.xj668yvm.activities.models.Characters
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("api/character/")
    suspend fun getCharacters(@Query("page") page: Int): Response<JsonObject>

    @GET("api/character/{id}")
    suspend fun getSingleCharacter(@Path("id") id: Int): Response<JsonObject>


}