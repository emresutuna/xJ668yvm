package com.example.xj668yvm.activities.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        const val MAIN_URL = "https://rickandmortyapi.com/"
        private fun retrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        val apiCharacterService: CharacterService by lazy {
            retrofit().create(CharacterService::class.java)
        }
    }



}
