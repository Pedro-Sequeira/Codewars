package com.example.android.codewars.utils

import com.example.android.codewars.models.User
import java.text.SimpleDateFormat
import java.util.*

fun convertRankToFormatted(user: User): String {
    return "#${user.leaderboardPosition}"
}

fun formatDate(fromDate: String): String {
    var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val date = simpleDateFormat.parse(fromDate)!!

    simpleDateFormat = SimpleDateFormat("MMM dd, yyyy - HH:mm:ss", Locale.getDefault())
    return simpleDateFormat.format(date)
}