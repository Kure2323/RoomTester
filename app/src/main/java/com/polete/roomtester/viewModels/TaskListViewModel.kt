package com.polete.roomtester.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.polete.roomtester.data.model.Task
import com.polete.roomtester.data.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskListViewModel(private val repo: TaskRepository) : ViewModel() {

    val tasks: StateFlow<List<Task>> =
        repo.getAllTask()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun addTask(task: Task) {
        viewModelScope.launch {
            repo.insertTask(task)
        }
    }

}


/**
 * Totalmente necesario el 'Factory', con esto se pueden crear los viewModels
 * en cada 'composable' que lo necesite.
 */
class TaskListViewModelFactory(private val repo: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskListViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}