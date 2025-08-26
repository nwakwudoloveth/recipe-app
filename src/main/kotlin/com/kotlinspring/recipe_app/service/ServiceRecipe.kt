package com.kotlinspring.recipe_app.service

import com.kotlinspring.recipe_app.dto.RecipeDTO
import com.kotlinspring.recipe_app.entity.Recipe
import com.kotlinspring.recipe_app.recipeRepository.RecipeRepository
import org.springframework.stereotype.Service

@Service

class ServiceRecipe (val recipeRepository:RecipeRepository) {


    fun addRecipe(recipeDTO: RecipeDTO): RecipeDTO {
        val recipeEntity = recipeDTO.let {
            Recipe(id = null, title = it.title, ingredients = it.ingredients, steps = it.steps)
        }
        recipeRepository.save(recipeEntity)

        return recipeEntity.let {
            RecipeDTO(
                id = it.id,
                title = it.title,
                ingredients = it.ingredients,
                steps = it.steps
            )
        }
    }


    fun retrieveAllRecipes(): List<RecipeDTO> =
        recipeRepository.findAll()
            .map { recipe ->
                RecipeDTO(recipe.id, recipe.title, recipe.ingredients, recipe.steps)
            }


    fun retrieveRecipeById(recipeID: Int): RecipeDTO {
        val recipe = recipeRepository.findById(recipeID)
            .orElseThrow { NoSuchElementException("Recipe with ID $recipeID not found") }

        return RecipeDTO(
            id = recipe.id,
            title = recipe.title,
            ingredients = recipe.ingredients,
            steps = recipe.steps
        )
    }
}