package com.example.android.networkcall.network.responses

import com.example.android.networkcall.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>
)