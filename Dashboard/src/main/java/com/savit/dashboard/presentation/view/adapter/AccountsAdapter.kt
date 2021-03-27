package com.savit.dashboard.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.savit.core.adapter.BaseListAdapter
import com.savit.core.extension.getThemeColor
import com.savit.dashboard.R
import com.savit.dashboard.databinding.AdapterAccountBinding
import com.savit.dashboard.presentation.model.AccountUiModel

class AccountsAdapter(val select: (Int) -> Unit) :
    BaseListAdapter<AccountUiModel, AdapterAccountBinding>(AccountDiffUtil) {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): AdapterAccountBinding {
        return AdapterAccountBinding.inflate(inflater, parent, false)
    }

    override fun onBindItem(binding: AdapterAccountBinding, item: AccountUiModel, position: Int) {
        if (item.isSelected) {
            binding.background.setBackgroundColor(binding.root.context.getThemeColor(R.attr.colorPrimary))
            binding.titleTextView.setTextColor(binding.root.context.getThemeColor(R.attr.colorOnPrimary))
            binding.amountTextView.setTextColor(binding.root.context.getThemeColor(R.attr.colorOnPrimary))
        } else {
            binding.background.setBackgroundColor(binding.root.context.getThemeColor(android.R.attr.colorBackground))
            binding.titleTextView.setTextColor(binding.root.context.getThemeColor(R.attr.colorSecondary))
            binding.amountTextView.setTextColor(binding.root.context.getThemeColor(R.attr.colorSecondary))
        }

        binding.amountTextView.text = item.amount
        binding.titleTextView.text = item.name
        binding.card.setOnClickListener { select(item.id) }
    }
}

object AccountDiffUtil : DiffUtil.ItemCallback<AccountUiModel>() {
    override fun areItemsTheSame(oldItem: AccountUiModel, newItem: AccountUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AccountUiModel, newItem: AccountUiModel): Boolean {
        return oldItem == newItem
    }
}