package com.example.puzzleherexamenandroid.data.network

import com.example.puzzleherexamenandroid.model.Puzzle
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://puzzleapiandroid20200809163632.azurewebsites.net"



private val moshi = Moshi.Builder().
add(KotlinJsonAdapterFactory())
    .build()


interface PuzzleApiService{
    @GET("/api/Puzzle")
    suspend fun getPuzzles(): List<Puzzle>
}

object Network {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .build()

    val puzzle: PuzzleApiService = retrofit.create(PuzzleApiService::class.java)
}