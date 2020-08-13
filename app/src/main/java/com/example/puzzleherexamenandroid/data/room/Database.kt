package com.example.puzzleherexamenandroid.data.room

import android.app.Dialog
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.puzzleherexamenandroid.data.room.databaseModels.DialogueDatabase
import com.example.puzzleherexamenandroid.data.room.databaseModels.DialogueLineDatabase
import com.example.puzzleherexamenandroid.data.room.databaseModels.PuzzleDatabase
import com.example.puzzleherexamenandroid.model.Dialogue
import com.example.puzzleherexamenandroid.model.DialogueLine
import com.example.puzzleherexamenandroid.model.Puzzle

@Database(entities = [PuzzleDatabase::class, DialogueDatabase::class, DialogueLineDatabase::class], version =3,  exportSchema = false)
abstract class puzzleDatabase : RoomDatabase(){
    abstract val puzzleDatabaseDao: PuzzleDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: puzzleDatabase? = null

        fun getInstance(context: Context): puzzleDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        puzzleDatabase::class.java,
                        "puzzleDatabase"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}