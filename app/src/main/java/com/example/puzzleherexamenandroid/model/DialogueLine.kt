package com.example.puzzleherexamenandroid.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DialogueLine (
    val dialogueLineId: Int,
    val text: String,
    val speaking: Int
    ): Parcelable