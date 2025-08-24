package com.kotlinspring.recipe_app.dto
import jakarta.validation.constraints.NotBlank


data class RecipeDTO (

    val id : Int?,
    @field:NotBlank(message = "Title must not be blank")
    val title: String,
    val ingredients: List<String>,
    val steps: List<String>
)
