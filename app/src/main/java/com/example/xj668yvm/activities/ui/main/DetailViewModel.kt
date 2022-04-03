package com.example.xj668yvm.activities.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xj668yvm.activities.models.Characters
import com.example.xj668yvm.activities.repository.CharacterServiceRepository
import com.example.xj668yvm.activities.util.ResponseHandler
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel(private var characterId:Int) : ViewModel() {
     var characterDetail: MutableLiveData<ResponseHandler<JsonObject>> = MutableLiveData()
    private var characterServiceRepository = CharacterServiceRepository()

    init {
        callCharacterDetail()
    }

    private fun callCharacterDetail() {
        viewModelScope.launch(Dispatchers.Main) {
            characterDetail.postValue(ResponseHandler.Loading())
            val response = characterServiceRepository.getSingleCharacter(
                characterId
            )
            characterDetail.postValue(handleRequest(response))
        }
    }

    private fun handleRequest(response: Response<JsonObject>): ResponseHandler<JsonObject> {

        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ResponseHandler.Success(resultResponse)
            }
        }
        response.errorBody()?.let { resultResponse ->
            val gson = Gson()
            val type = object : TypeToken<JsonObject>() {}.type
            val errorResponse: JsonObject? =
                gson.fromJson(
                    resultResponse.charStream(),
                    type
                )
            return ResponseHandler.Error(errorResponse!!)
        }
        return ResponseHandler.Error(JsonObject())
    }
}