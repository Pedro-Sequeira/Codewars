package com.example.android.codewars.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codewars.databinding.ListItemChallengeBinding
import com.example.android.codewars.models.Challenge

class ChallengeAdapter(private val challengeClickListener: ChallengeClickListener) :
    PagedListAdapter<Challenge, ChallengeAdapter.ViewHolder>(CompletedChallengesDiffUtil) {

    companion object CompletedChallengesDiffUtil : DiffUtil.ItemCallback<Challenge>() {
        override fun areItemsTheSame(
            oldItem: Challenge,
            newItem: Challenge
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Challenge,
            newItem: Challenge
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val challenge = getItem(position)
        holder.bind(challenge!!, challengeClickListener)
    }

    class ViewHolder private constructor(private val binding: ListItemChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            challenge: Challenge,
            challengeClickListener: ChallengeClickListener
        ) {
            binding.challenge = challenge
            binding.challengeClickListener = challengeClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemChallengeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ChallengeClickListener(val clickListener: (challengeId: String) -> Unit) {
        fun onClick(challenge: Challenge) = clickListener(challenge.id)
    }
}