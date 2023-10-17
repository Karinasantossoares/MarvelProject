package com.project.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.persistence.model.ResultsEntity

@Database(exportSchema = false, entities = [ResultsEntity::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun characterDao(): com.project.persistence.dao.CharacterDao

    companion object {
        fun instance(context: Context) : AppDataBase =
            Room.databaseBuilder(context, AppDataBase::class.java, "AppDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }
}
