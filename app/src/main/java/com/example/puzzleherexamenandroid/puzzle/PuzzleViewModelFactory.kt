package com.example.puzzleherexamenandroid.puzzle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.puzzleherexamenandroid.model.Puzzle

class PuzzleViewModelFactory(
    private val puzzle: Puzzle
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PuzzleViewModel::class.java)) {
            return PuzzleViewModel(puzzle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}