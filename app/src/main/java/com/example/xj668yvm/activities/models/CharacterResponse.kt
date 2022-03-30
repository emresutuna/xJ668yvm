package com.example.xj668yvm.activities.models

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    var info: Info? = Info(),

    @SerializedName("results")
    var results: ArrayList<Results> = arrayListOf()
) {

}