package com.koko.foodiekotlin.data

import com.koko.foodiekotlin.data.database.RecipesDao
import com.koko.foodiekotlin.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSource @Inject constructor(
        private val recipeDao: RecipesDao
){
     fun readDatabase(): Flow<List<RecipesEntity>>  {
        return recipeDao.readRecipes()
    }

   suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipeDao.insertRecipes(recipesEntity)
    }
}