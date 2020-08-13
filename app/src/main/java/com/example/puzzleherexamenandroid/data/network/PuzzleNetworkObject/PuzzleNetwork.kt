package com.example.puzzleherexamenandroid.data.network.PuzzleNetworkObject

data class PuzzleNetwork (
    val puzzleId: Int,
    val title: String,
    val prompt: String,
    val answer: String,
    val dialogue: DialogueNetwork

)