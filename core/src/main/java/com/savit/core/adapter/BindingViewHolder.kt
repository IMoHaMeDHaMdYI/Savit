package com.savit.core.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BindingViewHolder<out VB : ViewBinding>(val binding: VB) :
    RecyclerView.ViewHolder(binding.root)
