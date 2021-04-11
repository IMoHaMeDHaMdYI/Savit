package com.savit.account.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import com.savit.account.databinding.AdapterAccountBinding
import com.savit.account.presentation.model.SelectableAccountUiModel
import com.savit.core.adapter.BaseListAdapter

class SelectAccountsAdapter(private val select: (id: Long) -> Unit) :
    BaseListAdapter<SelectableAccountUiModel, AdapterAccountBinding>(AccountDiffUtil) {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): AdapterAccountBinding {
        return AdapterAccountBinding.inflate(inflater, parent, false)
    }

    override fun onBindItem(
        binding: AdapterAccountBinding,
        item: SelectableAccountUiModel,
        position: Int
    ) {
        binding.selectedImageView.isVisible = item.isSelected
        binding.accountNameTextView.text = item.name
        binding.root.setOnClickListener { select(item.id) }
    }
}

object AccountDiffUtil : DiffUtil.ItemCallback<SelectableAccountUiModel>() {
    override fun areItemsTheSame(
        oldItem: SelectableAccountUiModel,
        newItem: SelectableAccountUiModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: SelectableAccountUiModel,
        newItem: SelectableAccountUiModel
    ): Boolean {
        return newItem == oldItem
    }
}
