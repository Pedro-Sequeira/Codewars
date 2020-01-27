package com.example.android.codewars.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codewars.R
import com.example.android.codewars.models.User
import com.example.android.codewars.network.ApiStatus
import com.example.android.codewars.views.adapters.UserAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.layout_progress_bar.view.*

@BindingAdapter("usersList")
fun bindRecyclerViewUsers(recyclerView: RecyclerView, data: List<User>?) {
    val adapter = recyclerView.adapter as? UserAdapter
    adapter?.submitList(data)
    adapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            recyclerView.scrollToPosition(0)
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            recyclerView.scrollToPosition(0)
        }
    })
}

@BindingAdapter("leaderboardPosition")
fun TextView.setLeaderboardRankFormatted(user: User) {
    user.let {
        text = convertRankToFormatted(user)
    }
}

@BindingAdapter("dateFormatted")
fun TextView.setDateFormatted(date: String?) {
    date.let {
        text = formatDate(date)
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(progressBar: View, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
            progressBar.progress_bar_text.text =
                progressBar.context.getString(
                    when (progressBar.id) {
                        R.id.progress_bar_challenges -> R.string.challenge_loading_text
                        R.id.progress_bar_users -> R.string.users_loading_text
                        else -> R.string.details_loading_text
                    }
                )
        }
        ApiStatus.ERROR -> {
            progressBar.visibility = View.GONE
            Snackbar.make(
                progressBar,
                progressBar.context.getString(
                    when (progressBar.id) {
                        R.id.progress_bar_users -> {
                            R.string.user_not_found
                        }
                        else -> {
                            R.string.details_error
                        }
                    }
                ),
                Snackbar.LENGTH_LONG
            ).show()
        }
        ApiStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
    }
}