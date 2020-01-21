package com.example.android.codewars.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String,
    val name: String,
    val honor: Int,
    val clan: String,
    val leaderboardPosition: Int
) : Parcelable