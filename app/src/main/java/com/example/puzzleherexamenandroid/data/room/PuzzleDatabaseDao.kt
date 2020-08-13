package com.example.puzzleherexamenandroid.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.puzzleherexamenandroid.data.room.databaseModels.PuzzleDatabase
import com.example.puzzleherexamenandroid.data.room.databaseModels.PuzzleWithDialogueDatabase
import com.example.puzzleherexamenandroid.model.Puzzle

@Dao
interface PuzzleDatabaseDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(puzzle: List<PuzzleWithDialogueDatabase>)

    @Transaction
    @Query("SELECT * from puzzle_table ORDER BY puzzleId DESC")
    fun getAllPuzzles(): LiveData<List<PuzzleWithDialogueDatabase>>
}