package com.example.android.codewars.utils

import com.example.android.codewars.models.User

fun convertRankToFormatted(user: User): String {
    return "#${user.leaderboardPosition}"
}