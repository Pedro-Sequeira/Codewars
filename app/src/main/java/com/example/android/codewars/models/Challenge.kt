package com.example.android.codewars.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Challenge(
    val id: String,
    val name: String?,
    val description: String?,
    val completedAt: String?
)