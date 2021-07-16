package com.savit

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity() {

    private val viewModel: ProductsViewModel by viewModels()
    private val productsAdapter = ProductsAdapter(
        onAddToCartClick = { product -> viewModel.addToCart(productId = product.id) },
        onRemoveFromCartClick = { product -> viewModel.removeFromCart(productId = product.id) },
        onFavoritesClick = { product -> viewModel.toggleFavorite(productId = product.id) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        productsRecyclerView.adapter = productsAdapter
        viewModel.productsLiveData().observe(this) { products ->
            productsAdapter.submitList(products)
        }
    }
}