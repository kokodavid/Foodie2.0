package com.koko.foodiekotlin.data

import com.koko.foodiekotlin.data.network.FoodRecipeApi
import com.koko.foodiekotlin.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
        private val foodRecipeApi: FoodRecipeApi
){

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return  foodRecipeApi.getRecipes(queries)
    }

}