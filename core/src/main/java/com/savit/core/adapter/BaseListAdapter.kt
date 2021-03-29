package com.savit.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T, VB : ViewBinding>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BindingViewHolder<VB>>(diffCallback) {

    abstract fun onCreateBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    abstract fun onBindItem(binding: VB, item: T, position: Int)

    protected open fun onBindItem(binding: VB, item: T, position: Int, payloads: MutableList<Any>) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<VB> {
        val binding = onCreateBinding(LayoutInflater.from(parent.context), parent)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<VB>, position: Int) {
        val item = getItem(position)
        holder.binding.root.tag = item
        onBindItem(holder.binding, item, position)
    }

    override fun onBindViewHolder(
        holder: BindingViewHolder<VB>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val item = getItem(position)
            holder.binding.root.tag = item
            onBindItem(holder.binding, item, position, payloads)
        }
    }
}
