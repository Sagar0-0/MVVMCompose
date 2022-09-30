package com.example.android.networkcall.repository

import com.example.android.networkcall.domain.model.Recipe

interface RecipeRepository {

    suspend fun search(token: String, page: Int, query: String): List<Recipe>

    suspend fun get(token: String, id: Int): Recipe

}