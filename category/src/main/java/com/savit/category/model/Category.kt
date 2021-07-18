package com.savit.category.model

data class Category(val id: Long, val name: String, val imageUrl: String)

val categories = listOf(
    Category(1, "Transportation", "https://image.flaticon.com/icons/png/512/630/630746.png"),
    Category(2, "Food", "https://image.flaticon.com/icons/png/512/630/630746.png"),
    Category(3, "Housing", "https://image.flaticon.com/icons/png/512/630/630746.png"),
    Category(4, "Utilities", "https://image.flaticon.com/icons/png/512/630/630746.png"),
    Category(5, "Entertainment", "https://image.flaticon.com/icons/png/512/630/630746.png"),
    Category(6, "Other", "https://image.flaticon.com/icons/png/512/630/630746.png"),
    Category(7, "Income", "https://i.pinimg.com/originals/75/c4/ec/75c4ecfd80927be929cb867eabb7f4c6.jpg"),
)
