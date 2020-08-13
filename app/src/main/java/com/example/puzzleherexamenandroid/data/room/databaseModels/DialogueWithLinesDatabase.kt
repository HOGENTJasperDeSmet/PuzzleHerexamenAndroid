package com.example.puzzleherexamenandroid.data.room.databaseModels

import androidx.room.Embedded
import androidx.room.Relation

data class DialogueWithLinesDatabase(
    @Embedded val dialogue:DialogueDatabase,
    @Relation(
        parentColumn = "dialogueId",
        entityColumn = "dialogueForeignkeyId"
    )
    val dialogueLines: List<DialogueLineDatabase>
)