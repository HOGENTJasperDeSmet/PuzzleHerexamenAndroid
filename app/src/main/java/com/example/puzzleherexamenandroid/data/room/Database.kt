package com.example.puzzleherexamenandroid.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.puzzleherexamenandroid.model.Puzzle

@Database(entities = [Puzzle::class], version =9,  exportSchema = false)
abstract class PuzzleDatabase : RoomDatabase(){
    abstract val puzzleDatabaseDao: PuzzleDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: PuzzleDatabase? = null

        fun getInstance(context: Context): PuzzleDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = databaseBuilder(
                        context.applicationContext,
                        PuzzleDatabase::class.java,
                        "puzzleDatabase"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}