package com.project.home.data.local

@Entity(tableName = "exemplo")
data class ExemploEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val idade: Int
)