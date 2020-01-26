package com.example.android.codewars.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthoredChallenge(
    val id: String,
    val name: String,
    val description: String
)