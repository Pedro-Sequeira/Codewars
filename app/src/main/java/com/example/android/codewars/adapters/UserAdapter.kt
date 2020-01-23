package com.example.android.codewars.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codewars.databinding.ListItemUserBinding
import com.example.android.codewars.domainModels.User

private const val USERS_TO_SHOW_LIMIT = 5

class UserAdapter : ListAdapter<User, UserAdapter.ViewHolder>(UserDiffCallback) {

    companion object UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.username == newItem.username
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user!!)
    }

    class ViewHolder private constructor(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemUserBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class UserListener(val clickListener: (username: String) -> Unit) {
        fun onClick(user: User) = clickListener(user.username)
    }

    override fun getItemCount(): Int {
        if (super.getItemCount() > USERS_TO_SHOW_LIMIT) {
            return USERS_TO_SHOW_LIMIT
        }
        return super.getItemCount()
    }
}