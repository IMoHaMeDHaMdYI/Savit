package com.savit.dashboard.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.savit.core.adapter.BaseListAdapter
import com.savit.dashboard.databinding.AdapterPlanOverviewBinding
import com.savit.dashboard.presentation.model.PlanEntityUiModel

class PlanOverViewAdapter :
    BaseListAdapter<PlanEntityUiModel, AdapterPlanOverviewBinding>(PlanEntityDiffUtil) {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): AdapterPlanOverviewBinding {
        return AdapterPlanOverviewBinding.inflate(inflater, parent, false)
    }

    override fun onBindItem(
        binding: AdapterPlanOverviewBinding,
        item: PlanEntityUiModel,
        position: Int
    ) {
        binding.entityTextView.text = item.name
        binding.targetTextView.text = item.amount.toInt().toString()
        binding.progress.setProgress(item.progress, true)
    }

    override fun onBindItem(
        binding: AdapterPlanOverviewBinding,
        item: PlanEntityUiModel,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.firstOrNull() is Boolean)
            binding.progress.setProgress(item.progress, true)
        else
            super.onBindItem(binding, item, position, payloads)
    }
}

object PlanEntityDiffUtil : DiffUtil.ItemCallback<PlanEntityUiModel>() {
    override fun areItemsTheSame(oldItem: PlanEntityUiModel, newItem: PlanEntityUiModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: PlanEntityUiModel,
        newItem: PlanEntityUiModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: PlanEntityUiModel, newItem: PlanEntityUiModel): Any? {
        return if (oldItem.progress == newItem.progress)
            super.getChangePayload(oldItem, newItem)
        else true
    }
}