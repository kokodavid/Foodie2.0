package com.koko.foodiekotlin.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.koko.foodiekotlin.models.FoodRecipe
import com.koko.foodiekotlin.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity (
        var foodRecipe: FoodRecipe
  )
{
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}