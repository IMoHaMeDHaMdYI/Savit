package com.savit

data class Product(
    val id: Int,
    val quantity: Int,
    val price: Float,
    val name: String,
    val imgUrl: String,
    val isFavorite: Boolean
)
