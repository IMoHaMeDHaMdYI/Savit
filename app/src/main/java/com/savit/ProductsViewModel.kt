package com.savit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductsViewModel : ViewModel() {

    private val products = MutableLiveData<List<Product>>()
    fun productsLiveData(): LiveData<List<Product>> = products
    private fun productsList(): List<Product> = products.value ?: listOf()

    init {
        products.value = ProductProvider.products
    }

    fun addToCart(productId: Int) {
        val updatedProductList = productsList().map { product ->
            if (product.id == productId) {
                product.copy(quantity = product.quantity + 1)
            } else {
                product
            }
        }
        products.value = updatedProductList
    }

    fun removeFromCart(productId: Int) {
        val updatedProductList = productsList().map { product ->
            if (product.id == productId) {
                product.copy(quantity = product.quantity - 1)
            } else {
                product
            }
        }
        products.value = updatedProductList
    }

    fun toggleFavorite(productId: Int) {
        val updatedProductList = productsList().map { product ->
            if (product.id == productId) {
                product.copy(isFavorite = product.isFavorite.not())
            } else {
                product
            }
        }
        products.value = updatedProductList
    }
}