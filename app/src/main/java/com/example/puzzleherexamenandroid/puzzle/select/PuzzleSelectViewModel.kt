package com.example.puzzleherexamenandroid.puzzle.select

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.*
import com.example.puzzleherexamenandroid.data.network.Network
import com.example.puzzleherexamenandroid.data.repository.PuzzleRepository
import com.example.puzzleherexamenandroid.data.room.puzzleDatabase.Companion.getInstance
import com.example.puzzleherexamenandroid.model.Puzzle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PuzzleSelectViewModel(application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val _response = MutableLiveData<String>()

    private val database = getInstance(application)
    private val puzzleRepository = PuzzleRepository(database,application.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    init {
        viewModelScope.launch{
            puzzleRepository.refreshPuzzles()
        }
    }
    val puzzles = puzzleRepository.puzzles

    //navigation
    private val _navigateToSelectedPuzzle = MutableLiveData<Puzzle>()
    val navigateToSelectedPuzzle : LiveData<Puzzle>
    get() = _navigateToSelectedPuzzle

    fun displayDialogue(puzzle: Puzzle){
        _navigateToSelectedPuzzle.value = puzzle
    }
    fun displayDialogueComplete(){
        _navigateToSelectedPuzzle.value = null
    }


    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PuzzleSelectViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PuzzleSelectViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}

