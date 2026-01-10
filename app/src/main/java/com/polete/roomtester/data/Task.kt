package com.polete.roomtester.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")

data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val body: String
)
