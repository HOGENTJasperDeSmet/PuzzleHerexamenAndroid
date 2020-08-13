package com.example.puzzleherexamenandroid.data.network.PuzzleNetworkObject

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


data class DialogueNetwork (
    val dialogueId: Int,
    val char1avatar: String,
    val char2avatar: String
)