package com.example.android.codewars.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.codewars.domainModels.User

@Entity(tableName = "user_search_history_table")
data class UserDB constructor(
    @PrimaryKey
    val username: String
)

fun List<UserDB>.asDomainModel(): List<User> {
    return map {
        User(
            username = it.username
        )
    }
}