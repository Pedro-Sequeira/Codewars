package com.example.android.codewars.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.codewars.models.User

@Entity
data class DatabaseUser constructor(
    @PrimaryKey
    val username: String
)

fun DatabaseUser.asDomainModel(): User {
    return User(
        username = username
    )
}