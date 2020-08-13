package com.example.puzzleherexamenandroid.data.room.databaseModels

import androidx.room.Embedded
import androidx.room.Relation
import com.example.puzzleherexamenandroid.model.Puzzle

data class PuzzleWithDialogueDatabase(
    @Embedded val puzzle : PuzzleDatabase,
    @Relation(
        entity = DialogueWithLinesDatabase::class,
        parentColumn = "puzzleId",
        entityColumn = "puzzleDialogueId"
    )
    val dialogueWithLines: DialogueWithLinesDatabase
)
fun List<PuzzleWithDialogueDatabase>.asDomainModel(): List<Puzzle> {
    return map {
        Puzzle(it.puzzle.puzzleId,it.puzzle.title,it.puzzle.prompt,it.puzzle.answer,null)
    }
}