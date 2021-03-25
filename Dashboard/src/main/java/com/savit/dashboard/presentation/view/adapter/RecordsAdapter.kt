package com.savit.dashboard.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.savit.core.adapter.BaseListAdapter
import com.savit.dashboard.databinding.AdapterRecordBinding
import com.savit.dashboard.presentation.model.RecordUiModel

class RecordsAdapter : BaseListAdapter<RecordUiModel, AdapterRecordBinding>(RecordDiffUtil) {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): AdapterRecordBinding {
        return AdapterRecordBinding.inflate(inflater, parent, false)
    }

    override fun onBindItem(binding: AdapterRecordBinding, item: RecordUiModel, position: Int) {
        binding.apply {
            nameTextView.text = item.text
            accountTextView.text = item.account.name
            Glide.with(binding.root.context)
                .load(item.category.imageUrl)
                .transform(CircleCrop())
                .into(binding.categoryImageView)
            amountTextView.text = item.amount
            dateTextView.text = item.time
        }
    }
}

object RecordDiffUtil : DiffUtil.ItemCallback<RecordUiModel>() {
    override fun areItemsTheSame(oldItem: RecordUiModel, newItem: RecordUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecordUiModel, newItem: RecordUiModel): Boolean {
        return oldItem == newItem
    }
}
