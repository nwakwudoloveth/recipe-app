package com.kotlinspring.recipe_app.entity

import jakarta.persistence.*

@Entity
@Table(name = "Recipes")

data class Recipe(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int?,
    val title: String,
    val ingredients: List<String>,
    val steps: List<String>
)
