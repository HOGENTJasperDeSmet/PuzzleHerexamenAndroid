package com.example.puzzleherexamenandroid.data.room.databaseModels

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "dialogueLine_table")
@Parcelize
data class DialogueLineDatabase (
    @PrimaryKey
    val dialogueLineId: Int,
    val line: String,
    val speaking: Int,
    val dialogueForeignkeyId: Int
    ): Parcelable