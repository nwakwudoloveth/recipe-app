package com.kotlinspring.recipe_app.recipeRepository

import com.kotlinspring.recipe_app.entity.Recipe
import org.springframework.data.repository.CrudRepository

interface RecipeRepository:CrudRepository<Recipe, Int> {
}

