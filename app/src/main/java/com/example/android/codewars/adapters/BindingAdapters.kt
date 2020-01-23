package com.example.android.codewars.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codewars.domainModels.User

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<User>?) {
    recyclerView.scrollToPosition(0)
    (recyclerView.adapter as? UserAdapter)?.submitList(data)
}