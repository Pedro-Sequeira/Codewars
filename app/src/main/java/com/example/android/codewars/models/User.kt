package com.example.android.codewars.models

data class User(
    val username: String,
    val score: Int,
    val rank: String,
    val leaderboardPosition: Long?,
    val bestLanguage: String
)