package com.example.puzzleherexamenandroid.dialogue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.puzzleherexamenandroid.model.Puzzle

class DialogueViewModelFactory(
private val puzzle: Puzzle
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DialogueViewModel::class.java)) {
            return DialogueViewModel(puzzle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}