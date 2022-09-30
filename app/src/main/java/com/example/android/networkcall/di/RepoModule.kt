package com.example.android.networkcall.di

import com.example.android.networkcall.network.RecipeService
import com.example.android.networkcall.network.model.RecipeDtoMapper
import com.example.android.networkcall.repository.RecipeRepository
import com.example.android.networkcall.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepository_Impl(recipeService,recipeDtoMapper)
    }
}