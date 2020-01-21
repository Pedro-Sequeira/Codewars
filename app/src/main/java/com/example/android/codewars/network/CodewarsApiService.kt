package com.example.android.codewars.network

import com.example.android.codewars.models.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://www.codewars.com/api/v1/users/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CodewarsApiService {
    @GET("{username}")
    suspend fun getUser(@Path("username") name: String?): User
}

object CodewarsApi {
    val retrofitService: CodewarsApiService by lazy { retrofit.create(CodewarsApiService::class.java) }
}
