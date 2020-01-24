package com.example.android.codewars.adapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codewars.domainModels.User
import com.example.android.codewars.repository.ApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<User>?) {
    val adapter = recyclerView.adapter as? UserAdapter
    adapter?.submitList(data)
    adapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            recyclerView.scrollToPosition(0)
        }
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            recyclerView.scrollToPosition(0)
        }
        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            recyclerView.scrollToPosition(0)
        }
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            recyclerView.scrollToPosition(0)
        }
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            recyclerView.scrollToPosition(0)
        }
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
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
        }
        ApiStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
    }
}