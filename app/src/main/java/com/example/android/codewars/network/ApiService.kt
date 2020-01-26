package com.example.android.codewars.network

import com.example.android.codewars.models.Challenge
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val PAGE_SIZE = 100
const val API_FIRST_PAGE = 0

private const val BASE_URL = "https://www.codewars.com/api/v1/"

interface ApiService {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String?): UserDTO

    @GET("users/{username}/code-challenges/authored")
    suspend fun getAutheredChallenges(@Path("username") username: String?): AuthoredChallengesDTO

    @GET("users/{username}/code-challenges/completed")
    suspend fun getCompletedChallenges(
        @Path("username") username: String?,
        @Query("page") page: Int = API_FIRST_PAGE
    ): CompletedChallengesResponse?

    @GET("code-challenges/{challengeId}")
    suspend fun getChallenge(@Path("challengeId") challengeId: String?): Challenge
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object CodewarsApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}