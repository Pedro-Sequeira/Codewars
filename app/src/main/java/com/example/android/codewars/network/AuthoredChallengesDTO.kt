package com.example.android.codewars.network

import com.example.android.codewars.models.Challenge
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthoredChallengesDTO(
    val data: List<Challenge>
)