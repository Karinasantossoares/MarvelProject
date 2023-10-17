package com.project.persistence.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class ResultsEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    @Embedded
    val thumbnail: ThumbnailEntity,
    val page: Int
)

data class ThumbnailEntity(
    val path: String,
    val extension: String
)