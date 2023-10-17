package com.project.home.data.datasource.local

import com.project.persistence.dao.CharacterDao
import com.project.persistence.model.ResultsEntity

internal class LocalDataSourceImpl(val characterDao: CharacterDao) :
    LocalDataSource {

    override fun saveCharacter(characters: List<ResultsEntity>) {
        characterDao.insert(character = characters)
    }

    override fun getAllCharacters(page: Int): List<ResultsEntity> {
        return characterDao.getAllCharacter(page)
    }
}