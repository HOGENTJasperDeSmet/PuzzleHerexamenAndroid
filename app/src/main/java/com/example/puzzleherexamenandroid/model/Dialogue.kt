package com.example.puzzleherexamenandroid.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dialogue (
    val dialogueId: Int,
    val char1avatar: String,
    val char2Avatar: String,
    val dialogueLines: List<DialogueLine>
): Parcelable