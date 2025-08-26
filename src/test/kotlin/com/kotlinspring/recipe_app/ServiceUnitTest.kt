package com.kotlinspring.recipe_app

import com.kotlinspring.recipe_app.dto.RecipeDTO
import com.kotlinspring.recipe_app.entity.Recipe
import com.kotlinspring.recipe_app.recipeRepository.RecipeRepository
import com.kotlinspring.recipe_app.service.ServiceRecipe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Optional

class ServiceUnitTest {

    val recipeRepository = mockk<RecipeRepository>()
    private lateinit var serviceRecipe: ServiceRecipe
    @BeforeEach
    fun setUp() {
        // Clear any previous calls
        clearMocks(recipeRepository)
        serviceRecipe = ServiceRecipe(recipeRepository)

    }
    @Test
    fun `returns correct recipe DTO` (){
        val recipeId = 1
        val recipeEntity = Recipe(id = recipeId, title = "Pizza", ingredients = listOf("Cheese, Dough"), steps = listOf("Bake"))
        every { recipeRepository.findById(recipeId) } returns Optional.of(recipeEntity)
        val result = serviceRecipe.retrieveRecipeById(recipeId)
        assertEquals(recipeId, result.id)
        assertEquals("Pizza", result.title)
        assertEquals(listOf("Cheese, Dough"), result.ingredients)
        assertEquals(listOf("Bake"), result.steps)

    }

     @Test
    fun `returns all recipes`(){
    val recipeEntity = Recipe(id = 1, title = "Pizza", ingredients = listOf("Cheese, Dough"), steps = listOf("Bake"))
    every { recipeRepository.findAll() } returns listOf(recipeEntity)
    val result = serviceRecipe.retrieveAllRecipes()

    assertEquals(1, result.size)
    assertEquals("Pizza", result[0].title)
    assertEquals(listOf("Cheese, Dough"), result[0].ingredients.toList())
    assertEquals(listOf("Bake"), result[0].steps)

    verify(exactly = 1) { recipeRepository.findAll() }
    }


    @Test
    fun `addRecipe saves recipe and returns correct DTO`() {

        val recipeDTO = RecipeDTO(
            id = null,
            title = "Pizza",
            ingredients = listOf("Cheese", "Dough"),
            steps = listOf("Bake")
        )
        every { recipeRepository.save(any()) } answers {firstArg<Recipe>()}
        val result = serviceRecipe.addRecipe(recipeDTO)

        assertEquals(recipeDTO.title, result.title)
        assertEquals(recipeDTO.ingredients, result.ingredients)
        assertEquals(recipeDTO.steps, result.steps)
        assertEquals(null, result.id) // ID should be null as in the function

        verify(exactly = 1) {
            recipeRepository.save(
                match {
                    it.title == recipeDTO.title &&
                            it.ingredients == recipeDTO.ingredients &&
                            it.steps == recipeDTO.steps &&
                            it.id == null
                }
            )
        }
    }

 }