package com.example.android.codewars.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codewars.databinding.ListItemChallengeBinding
import com.example.android.codewars.models.Challenge

class ChallengesAdapter :
    ListAdapter<Challenge, ChallengesAdapter.ViewHolder>(ChallengeDiffCallback) {

    companion object ChallengeDiffCallback : DiffUtil.ItemCallback<Challenge>() {

        override fun areItemsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val challenge = getItem(position)
        holder.bind(challenge!!)
    }

    class ViewHolder private constructor(private val binding: ListItemChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(challenge: Challenge) {
            binding.challenge = challenge
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemChallengeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}