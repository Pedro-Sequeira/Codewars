package com.example.android.codewars.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codewars.databinding.ListItemCompletedChallengeBinding
import com.example.android.codewars.models.CompletedChallenge

class CompletedChallengeAdapter(private val challengeClickListener: ChallengeClickListener) :
    PagedListAdapter<CompletedChallenge, CompletedChallengeAdapter.ViewHolder>(
        CompletedChallengesDiffUtil
    ) {

    companion object CompletedChallengesDiffUtil : DiffUtil.ItemCallback<CompletedChallenge>() {
        override fun areItemsTheSame(
            oldItem: CompletedChallenge,
            newItem: CompletedChallenge
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CompletedChallenge,
            newItem: CompletedChallenge
        ): Boolean {
            return oldItem.name == newItem.name && oldItem.completedAt == newItem.completedAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val completedChallenge = getItem(position)
        holder.bind(completedChallenge!!, challengeClickListener)
    }

    class ViewHolder private constructor(private val binding: ListItemCompletedChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            completedChallenge: CompletedChallenge,
            challengeClickListener: ChallengeClickListener
        ) {
            binding.completedChallenge = completedChallenge
            binding.challengeClickListener = challengeClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemCompletedChallengeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ChallengeClickListener(val clickListener: (challengeId: String) -> Unit) {
        fun onClick(challenge: CompletedChallenge) = clickListener(challenge.id)
    }
}