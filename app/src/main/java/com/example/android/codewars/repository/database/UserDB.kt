package com.example.android.codewars.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.codewars.models.User

@Entity(tableName = "user_search_history_table")
data class UserDB constructor(
    @PrimaryKey
    val username: String,
    val rank: String,
    val score: Int,
    val creationDate: Long
)

fun List<UserDB>.asDomainModel(): List<User> {
    return map {
        User(
            username = it.username,
            rank = it.rank,
            score = it.score
        )
    }
}