package com.tron.kkdayassiment.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tron.kkdayassiment.databinding.ItemHistoryBinding
import com.tron.shared.model.History

class HistoryAdapter(
    val cancelHistory: (History) -> Unit,
    val copyText: (History) -> Unit,
    val browserPageIntent: (History) -> Unit,
) : ListAdapter<History, HistoryAdapter.HistoryViewHolder>(HistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.ibCancel.setOnClickListener { cancelHistory(item) }
        holder.binding.btCopy.setOnClickListener { copyText(item) }
        holder.binding.tvShortLink.setOnClickListener { browserPageIntent(item) }
    }

    class HistoryViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.tvOriginLink.text = history.original_link
            binding.tvShortLink.text = history.full_short_link
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): HistoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHistoryBinding.inflate(layoutInflater, parent, false)
                return HistoryViewHolder(binding)
            }
        }
    }

    class HistoryDiffCallback : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem == newItem
        }
    }
}
