package com.example.android.codewars.network

import com.example.android.codewars.domainModels.User
import com.example.android.codewars.repository.database.UserDB
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class UserDTO(
    val success: Boolean?,
    val username: String,
    val honor: Int,
    val ranks: RanksDTO
) {
    @JsonClass(generateAdapter = true)
    data class RanksDTO(
        val overall: OverallDTO
    ) {
        @JsonClass(generateAdapter = true)
        data class OverallDTO(
            val name: String
        )
    }
}

fun UserDTO.asDomainModel(): User {
    return User(
        username = username,
        score = honor,
        rank = formatRank(ranks.overall.name, honor)
    )
}

fun UserDTO.asDatabaseModel(): UserDB {
    return UserDB(
        username = username,
        rank = formatRank(ranks.overall.name, honor),
        score = honor,
        creationDate = Calendar.getInstance().timeInMillis
    )
}

private fun formatRank(rank: String, score: Int): String {
    return "$rank ($score)"
}