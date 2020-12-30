package com.koko.foodiekotlin.util

class Constants {
    companion object {

        const val APIKEY = "74913db7a4d5403a99e582ae3e24db99"
        const val BASE_URL = "https://api.spoonacular.com"

        //API Query keys
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS= "fillIngredients"

        //ROOM DATABASE
        const val DATABASE_NAME = "recipes_database"
        const val RECIPES_TABLE = "recipes_table"

    }
}