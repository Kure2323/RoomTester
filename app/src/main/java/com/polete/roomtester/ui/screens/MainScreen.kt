package com.polete.roomtester.ui.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.polete.roomtester.data.model.Task
import com.polete.roomtester.data.repository.TaskRepository
import com.polete.roomtester.navigation.Screens
import com.polete.roomtester.ui.AddButton
import com.polete.roomtester.ui.AppViewDefault
import com.polete.roomtester.ui.TaskLabel
import com.polete.roomtester.viewModels.TaskListViewModel
import com.polete.roomtester.viewModels.TaskListViewModelFactory

@Composable
fun MainScreen(
    repository: TaskRepository,
    navController: NavController
) {

    val viewModel: TaskListViewModel = viewModel(
        factory = TaskListViewModelFactory(repository)
    )

    val tasks by viewModel.tasks.collectAsState()

    AppViewDefault(
        modifier = Modifier,
        title = "Main Page",
        content = { contentModifier ->

            Log.d("NAV", "Esto es la mainScreen")

            LazyColumn(
                modifier = contentModifier,
                userScrollEnabled = true
            ) {

                items(items = tasks) { task ->
                    TaskLabel(
                        task = task,
                        onClick = {
                            val taskId = task.id
                            Log.d("NAV", "Navigating with taskId=${task.id}")

                            navController.navigate(
                                Screens.taskDetailScreen.createRoute(taskId)
                            )
                        }
                    )
                }

            }

            AddButton(
                onClick = {navController.navigate(Screens.addTaskScreen.route)}
            )



        }
    )

}

@Preview(showSystemUi = true)
@Composable
fun MainScreen() {

    val tasks = listOf(
        Task(title = "Title", body = "Body of the body of the holy holy body"),
        Task(title = "Title", body = "Body of the body of the holy holy body"),
        Task(title = "Title", body = "Body of the body of the holy holy body"),
        Task(title = "Title", body = "Body of the body of the holy holy body")
    )

    AppViewDefault(
        modifier = Modifier,
        title = "Main Page",
        content = { contentModifier ->

            LazyColumn(
                modifier = contentModifier,
                userScrollEnabled = true
            ) {

                items(items = tasks) { task ->
                    TaskLabel(
                        task = task,
                        onClick = {
                            //Navigation
                        }
                    )
                }
            }
            AddButton(onClick = {})
        }
    )

}

