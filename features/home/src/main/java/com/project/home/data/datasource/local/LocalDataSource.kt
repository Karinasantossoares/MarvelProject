package com.project.home.data.datasource.local

import com.project.persistence.model.ResultsEntity

internal interface LocalDataSource {
    fun saveCharacter(characters: List<ResultsEntity>)
    fun getAllCharacters(page:Int): List<ResultsEntity>
}