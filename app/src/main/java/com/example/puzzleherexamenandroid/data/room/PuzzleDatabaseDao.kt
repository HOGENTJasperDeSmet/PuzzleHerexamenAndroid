package com.example.puzzleherexamenandroid.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.puzzleherexamenandroid.model.Puzzle

@Dao
interface PuzzleDatabaseDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(puzzle: List<Puzzle>)

    @Transaction
    @Query("SELECT * from puzzle_table")
    fun getAllPuzzles(): LiveData<List<Puzzle>>
}