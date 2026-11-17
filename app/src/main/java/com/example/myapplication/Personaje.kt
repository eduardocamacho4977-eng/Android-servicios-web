package com.example.myapplication

/**
 * Modelo de datos simple para representar un personaje de la API de Rick & Morty
 */
data class Personaje(
    val id: Int,
    val nombre: String,
    val especie: String,
    val urlImagen: String
)
