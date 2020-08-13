package com.example.puzzleherexamenandroid.data.repository

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.puzzleherexamenandroid.data.network.Network
import com.example.puzzleherexamenandroid.data.room.databaseModels.asDomainModel
import com.example.puzzleherexamenandroid.data.room.puzzleDatabase
import com.example.puzzleherexamenandroid.model.Puzzle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PuzzleRepository(private val database: puzzleDatabase,private val connectivityManager: ConnectivityManager) {
    val puzzles: LiveData<List<Puzzle>> = Transformations.map(database.puzzleDatabaseDao.getAllPuzzles()) {
        it.asDomainModel()
    }


    suspend fun refreshPuzzles(){
        if(internetConnected()){
            withContext(Dispatchers.IO){
                val puzzles = Network.puzzle.getPuzzles()
                //database.puzzleDatabaseDao.insertAll(puzzles)
            }
        }

    }
    private fun internetConnected(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }



}