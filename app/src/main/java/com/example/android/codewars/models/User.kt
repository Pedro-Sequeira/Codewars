package com.example.android.codewars.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_search_history_table")
data class User(
    @PrimaryKey
    val username: String
)