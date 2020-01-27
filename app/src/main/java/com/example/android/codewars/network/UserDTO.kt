package com.example.android.codewars.network

import com.example.android.codewars.models.User
import com.example.android.codewars.network.UserDTO.RanksDTO.RankDTO
import com.example.android.codewars.repository.database.UserDB
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class UserDTO(
    val success: Boolean?,
    val username: String,
    val honor: Int,
    val ranks: RanksDTO,
    val leaderboardPosition: Long?
) {
    var bestLanguage: String = findBestLanguage()

    private fun findBestLanguage(): String {
        var best: Map.Entry<String, RankDTO>? = null

        ranks.let {
            it.languages.let { language ->
                best = language.maxBy { rank ->
                    rank.value.score
                }
            }
        }

        return best.let {
            "${it?.key}, ${it?.value?.score}"
        }
    }

    @JsonClass(generateAdapter = true)
    data class RanksDTO(
        val overall: RankDTO,
        val languages: Map<String, RankDTO>
    ) {
        @JsonClass(generateAdapter = true)
        data class RankDTO(
            val name: String,
            val score: Int
        )
    }
}

fun UserDTO.asDomainModel(): User {
    return User(
        username = username,
        score = honor,
        rank = formatRank(ranks, honor),
        leaderboardPosition = leaderboardPosition,
        bestLanguage = bestLanguage
    )
}

fun UserDTO.asDatabaseModel(): UserDB {
    return UserDB(
        username = username,
        rank = formatRank(ranks, honor),
        score = honor,
        leaderboardPosition = leaderboardPosition,
        bestLanguage = bestLanguage,
        creationDate = Calendar.getInstance().timeInMillis
    )
}

private fun formatRank(rank: UserDTO.RanksDTO, score: Int): String {
    return "${rank.overall.name} ($score)"
}