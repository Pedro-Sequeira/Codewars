package com.example.android.codewars.ui.adapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codewars.R
import com.example.android.codewars.domainModels.User
import com.example.android.codewars.repository.ApiStatus
import com.google.android.material.snackbar.Snackbar

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<User>?) {
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

@BindingAdapter("apiStatus")
fun bindStatus(progressBar: View, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
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