package com.example.puzzleherexamenandroid.dialogue

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.puzzleherexamenandroid.model.Puzzle

class DialogueViewModel(puzzle: Puzzle) : ViewModel() {
    private val _selectedPuzzle = MutableLiveData<Puzzle>()
    val selectedPuzzle: LiveData<Puzzle>
        get() = _selectedPuzzle

    init{
        _selectedPuzzle.value = puzzle
    }
}