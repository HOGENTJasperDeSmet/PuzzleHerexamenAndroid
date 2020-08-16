package com.example.puzzleherexamenandroid.model
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity(tableName = "puzzle_table")
@Parcelize
data class Puzzle(
     @PrimaryKey
     val puzzleId: Int,
     val title: String,
     val prompt: String,
     val answer: String,
     val dialogue: String,
     val puzzleIcon: String,
     val char1Avatar: String,
     val char2Avatar: String,
     val puzzleImage: String
): Parcelable
