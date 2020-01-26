package com.example.android.codewars.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codewars.databinding.ListItemAuthoredChallengeBinding
import com.example.android.codewars.models.AuthoredChallenge

class AuthoredChallengesAdapter :
    ListAdapter<AuthoredChallenge, AuthoredChallengesAdapter.ViewHolder>(ChallengeDiffCallback) {

    companion object ChallengeDiffCallback : DiffUtil.ItemCallback<AuthoredChallenge>() {

        override fun areItemsTheSame(oldItem: AuthoredChallenge, newItem: AuthoredChallenge): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AuthoredChallenge, newItem: AuthoredChallenge): Boolean {
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

    class ViewHolder private constructor(private val binding: ListItemAuthoredChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(authoredChallenge: AuthoredChallenge) {
            binding.challenge = authoredChallenge
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemAuthoredChallengeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}