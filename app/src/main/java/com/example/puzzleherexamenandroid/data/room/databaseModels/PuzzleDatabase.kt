package com.example.puzzleherexamenandroid.data.room.databaseModels
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.puzzleherexamenandroid.model.Puzzle
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "puzzle_table")
@Parcelize
data class PuzzleDatabase (
     @PrimaryKey
     val puzzleId: Int,
     val title: String,
     val prompt: String,
     val answer: String

): Parcelable

fun List<PuzzleDatabase>.asDomainModel(): List<Puzzle> {
    return map {
        Puzzle(it.puzzleId,it.title,it.prompt,it.answer,null)
    }
}