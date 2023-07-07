package ru.dadyarri.choco.data.entities

data class Product(
    val title: String,
    val wholesalePrice: Double,
    val retailPrice: Double,
    val leftover: Double,
    val isByWeight: Boolean
)
