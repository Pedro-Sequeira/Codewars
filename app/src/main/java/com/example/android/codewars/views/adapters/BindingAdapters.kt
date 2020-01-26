package com.example.android.codewars.views.adapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codewars.R
import com.example.android.codewars.models.AuthoredChallenge
import com.example.android.codewars.models.User
import com.example.android.codewars.network.ApiStatus
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

@BindingAdapter("authoredChallengesList")
fun bindRecyclerViewAuthoredChallenges(recyclerView: RecyclerView, data: List<AuthoredChallenge>?) {
    val adapter = recyclerView.adapter as? AuthoredChallengesAdapter
    adapter?.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(progressBar: View, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            when (progressBar.id) {
                R.id.progress_bar_challenges -> {
                    progressBar.progress_bar_text.text =
                        progressBar.context.getString(R.string.challenge_loading_text)
                }
            }
            progressBar.visibility = View.VISIBLE
        }
        ApiStatus.ERROR -> {
            progressBar.visibility = View.GONE
            Snackbar.make(
                progressBar,
                progressBar.context.getString(R.string.user_not_found),
                Snackbar.LENGTH_SHORT
            ).show()
        }
        ApiStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
    }
}