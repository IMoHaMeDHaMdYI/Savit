package com.savit

import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DiffUtil

object ProductDiffUtil : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Product, newItem: Product): Any? {
        val bundle = bundleOf()
        if (oldItem.quantity != newItem.quantity) {
            bundle.putBoolean(QUANTITY_CHANGED, true)
        }
        if (oldItem.isFavorite != newItem.isFavorite) {
            bundle.putBoolean(FAVORITES_CHANGED, true)
        }
        return if (bundle.isEmpty) {
            null
        } else {
            bundle
        }
    }

    const val QUANTITY_CHANGED = "quantity_changed"
    const val FAVORITES_CHANGED = "favorites_changed"
}