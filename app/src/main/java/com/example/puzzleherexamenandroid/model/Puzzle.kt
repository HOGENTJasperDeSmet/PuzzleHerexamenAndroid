package com.example.puzzleherexamenandroid.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Puzzle(
     val puzzleId: Int,
     val title: String,
     val prompt: String,
     val answer: String,
     val dialogue: Dialogue?
): Parcelable