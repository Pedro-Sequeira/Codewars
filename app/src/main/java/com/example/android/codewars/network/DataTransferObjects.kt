package com.example.android.codewars.network

import com.example.android.codewars.database.DatabaseUser
import com.example.android.codewars.models.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserContainer(val user: User)

@JsonClass(generateAdapter = true)
data class NetworkUser(
    val username: String
)

fun UserContainer.asDomainModel(): User {
    return User(
        username = user.username
    )
}

fun UserContainer.asDatabaseModel(): DatabaseUser {
    return DatabaseUser(
        username = user.username
    )
}