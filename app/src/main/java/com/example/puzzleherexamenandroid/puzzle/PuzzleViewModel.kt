package com.example.puzzleherexamenandroid.puzzle

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.puzzleherexamenandroid.model.Puzzle

class PuzzleViewModel(puzzle: Puzzle) : ViewModel(){
    val strokeManager = StrokeManager()

    private val _selectedPuzzle = MutableLiveData<Puzzle>()
    val selectedPuzzle: LiveData<Puzzle>
        get() = _selectedPuzzle

    init{
        _selectedPuzzle.value = puzzle
    }
    fun resetInput(){
        strokeManager.reset()
    }
    fun strokeManagerInit(){
        strokeManager.setActiveModel("En_in")
        strokeManager.download()
    }

    fun correctAnswer(): Boolean {
        return _selectedPuzzle.value?.answer == strokeManager.getResult()?.text.toString()

    }
}