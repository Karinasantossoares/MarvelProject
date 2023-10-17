package com.project.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.persistence.model.ResultsEntity

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: List<ResultsEntity>)


    @Query("SELECT * FROM character where page = :page")
    fun getAllCharacter(page: Int): List<ResultsEntity>
}