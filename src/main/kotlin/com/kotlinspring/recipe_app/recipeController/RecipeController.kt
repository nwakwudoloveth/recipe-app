package com.kotlinspring.recipe_app.recipeController

import com.kotlinspring.recipe_app.dto.RecipeDTO
import com.kotlinspring.recipe_app.service.ServiceRecipe
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@ControllerAdvice
@RestController
@RequestMapping("v1/recipes")



class RecipeController(val serviceRecipe: ServiceRecipe) {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    fun addRecipe( @Valid @RequestBody recipeDTO: RecipeDTO): RecipeDTO {

        return serviceRecipe.addRecipe(recipeDTO)
    }


    @GetMapping
    fun retrieveAllRecipes(): List<RecipeDTO> {

        return serviceRecipe.retrieveAllRecipes()
    }

    @GetMapping("/{id}")
    fun retrieveRecipeById(@PathVariable id: Int): RecipeDTO {
        return serviceRecipe.retrieveRecipeById(id)
    }

}