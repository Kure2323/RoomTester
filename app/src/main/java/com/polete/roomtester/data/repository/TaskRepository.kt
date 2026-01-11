package com.polete.roomtester.data.repository

import com.polete.roomtester.data.database.DaoTask
import com.polete.roomtester.data.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: DaoTask) {

    fun getAllTask(): Flow<List<Task>> {
        return taskDao.getAllTask()
    }

    fun getTaskById(taskId: Long): Flow<Task?> {
        return taskDao.getTaskById(taskId)
    }

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

}