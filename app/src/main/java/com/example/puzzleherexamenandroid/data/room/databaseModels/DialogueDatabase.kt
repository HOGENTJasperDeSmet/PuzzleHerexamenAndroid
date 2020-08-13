package com.example.puzzleherexamenandroid.data.room.databaseModels

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "dialogue_table")
@Parcelize
data class DialogueDatabase (
    @PrimaryKey
    val dialogueId: Int,
    val prompt: String,
    val char1avatar: String,
    val char2avatar: String,
    val puzzleDialogueId : Int
): Parcelable

