package com.example.android.codewars.network

import com.example.android.codewars.database.UserDB
import com.example.android.codewars.domainModels.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDTO(
    val success: Boolean?,
    val username: String
)

fun UserDTO.asDomainModel(): User {
    return User(
        username = username
    )
}

fun UserDTO.asDatabaseModel(): UserDB {
    return UserDB(
        username = username
    )
}