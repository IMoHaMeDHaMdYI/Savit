package com.savit

object ProductProvider {
    private val images = listOf(
        "https://www.fivestartrading-holland.eu/images/product_images/original_images/schweppes_indian_tonic_PL.jpg",
        "https://media.spar.nl/productdetail/pringles-chips-original-165-Gram-2362465-7791.jpg",
        "https://www.chipsandcrisps.com/uploads/1/4/8/3/14831570/1581854609-tiger-kebab-3d-1_1.png",

        )
    val products = listOf(
        Product(
            id = 0,
            quantity = 0,
            price = 10f,
            name = "Schwepps",
            imgUrl = images[0],
            isFavorite = false
        ),
        Product(
            id = 1,
            quantity = 0,
            price = 20f,
            name = "Pringles",
            imgUrl = images[1],
            isFavorite = false
        ),
        Product(
            id = 22,
            quantity = 0,
            price = 30f,
            name = "Tiger Kebab",
            imgUrl = images[2],
            isFavorite = false
        ),
    )
}