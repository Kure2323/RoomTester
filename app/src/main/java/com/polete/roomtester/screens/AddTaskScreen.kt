package com.polete.roomtester.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.polete.roomtester.data.DaoTask
import com.polete.roomtester.viewModels.TaskListViewModel
import com.polete.roomtester.ui.AppViewDefault
import com.polete.roomtester.ui.FormTask
import com.polete.roomtester.viewModels.TaskListViewModelFactory

@Composable
fun AddTaskScreen(
    taskDao: DaoTask,
    navController: NavController,
) {

    val viewModel: TaskListViewModel = viewModel(
        factory = TaskListViewModelFactory(taskDao)
    )

    AppViewDefault(
        modifier = Modifier,
        title = "Add Task Page",
        content = {

            // Si se observa, 'task' es el nombre que se le otorga a la 'task'
            // que devuelve 'onAdd'
            FormTask(
                onAdd = { task ->
                    viewModel.addTask(task)
                    navController.navigate("main_screen")
                }
            )

        }
    )

}

@Preview(showSystemUi = true)
@Composable
fun AddTaskScreen() {

    AppViewDefault(
        modifier = Modifier,
        title = "Add Task Page",
        content = {

            // Si se observa, 'task' es el nombre que se le otorga a la 'task'
            // que devuelve 'onAdd'
            FormTask(
                onAdd = {}
            )

        }
    )

}
