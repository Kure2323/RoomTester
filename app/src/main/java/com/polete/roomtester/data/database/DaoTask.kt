package com.polete.roomtester.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.polete.roomtester.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoTask {
    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("select * from task ORDER BY id DESC")
    fun getAllTask(): Flow<List<Task>>

    // Query para buscar una task concreta en base a la 'id'
    // Es nullable pues cuando quiera borrar una task esta deberá poder tener un valor null,
    // pues en caso contrario crashará la App
    @Query("select * from task where id = :id")
    fun getTaskById(id: Long): Flow<Task?>
}