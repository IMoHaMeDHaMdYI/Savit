package com.savit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_product.view.*

class ProductsAdapter(
    private val onAddToCartClick: (Product) -> Unit,
    private val onRemoveFromCartClick: (Product) -> Unit,
    private val onFavoritesClick: (Product) -> Unit
) : ListAdapter<Product, ProductsAdapter.ProductViewHolder>(ProductDiffUtil) {

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position), onAddToCartClick, onRemoveFromCartClick, onFavoritesClick)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        (payloads.getOrNull(0) as? Bundle?)?.let { bundle ->
            val product = getItem(position)
            if (bundle.getBoolean(ProductDiffUtil.QUANTITY_CHANGED,false)) {
                holder.bindQuantity(product.quantity.toString())
            }
            if (bundle.getBoolean(ProductDiffUtil.FAVORITES_CHANGED)) {
                holder.bindFavorites(product.isFavorite)
            }
        } ?: super.onBindViewHolder(holder, position, payloads)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.adapter_product, parent, false)
            .let(::ProductViewHolder)
    }


    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(
            product: Product,
            onAddToCartClick: (Product) -> Unit,
            onRemoveFromCartClick: (Product) -> Unit,
            onFavoritesClick: (Product) -> Unit
        ) = itemView.apply {
            bindQuantity(product.quantity.toString())
            bindFavorites(product.isFavorite)
            nameTextView.text = product.name
            priceTextView.text = "\$${product.price}"
            Glide.with(context)
                .load(product.imgUrl)
                .into(productImageView)
            addToCartButton.setOnClickListener { onAddToCartClick(product) }
            removeFromCartButton.setOnClickListener { onRemoveFromCartClick(product) }
            favoritesImageView.setOnClickListener { onFavoritesClick(product) }
        }

        fun bindQuantity(quantity: String) = itemView.apply {
            quantityTextView.text = quantity
        }

        fun bindFavorites(isFavorite: Boolean) = itemView.apply {
            favoritesImageView.setImageResource(
                if (isFavorite) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24
            )
        }
    }

}
