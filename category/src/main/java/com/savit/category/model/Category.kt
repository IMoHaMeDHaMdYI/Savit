package com.savit.category.model

data class Category(val id: Long, val name: String, val imageUrl: String)

fun getCategory(id: Long) =
    Category(22, "Shopping", "https://image.flaticon.com/icons/png/512/630/630746.png")