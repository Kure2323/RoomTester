package com.polete.roomtester.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.polete.roomtester.data.model.Task
import com.polete.roomtester.data.repository.TaskRepository
import com.polete.roomtester.ui.AppViewDefault
import com.polete.roomtester.viewModels.TaskDetailViewModel
import com.polete.roomtester.viewModels.TaskDetailViewModelFactory

@Composable
fun TaskDetailsScreen(
    modifier: Modifier = Modifier,
    repository: TaskRepository,
    navController: NavController,
    taskId: Long
) {



    //Creación del viewModel necesario para obtener la task en cuestión
    val viewModel: TaskDetailViewModel = viewModel(
        factory = TaskDetailViewModelFactory(repository, taskId)
    )

    val task by viewModel.task.collectAsState()

    AppViewDefault(
        modifier = modifier.fillMaxSize(),
        title = "Task Details",
        content = { paddingValues ->

            // En caso de no ser null entonces ->
            task?.let {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = paddingValues.padding(20.dp)
                ) {
                    Spacer(Modifier.size(20.dp))
                    Text(it.title)
                    Spacer(Modifier.size(20.dp))
                    Text(
                        text = it.body,
                        textAlign = TextAlign.Left
                    )

                    Column(
                        modifier = Modifier.fillMaxSize().padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Button(
                            onClick = {
                                navController.popBackStack()
                                viewModel.deleteTask(it)
                                      },
                            modifier = Modifier.fillMaxWidth().height(50.dp)
                        ) {
                            Text("Delete Task")
                        }
                    }


                }
                // En caso de ser null entonces ->
            } ?: Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Pantalla de 'loading' cuando una 'task' es borrada
                Text("Loading...")
            }

        }
    )

}

@Preview(showSystemUi = true)
@Composable
fun TaskDetailsScreen() {

    val task = Task(title = "Título", body = "Cuerpo de la task")

    AppViewDefault(
        modifier = Modifier.fillMaxSize(),
        title = "Task Details",
        content = { paddingValues ->

            Column(
                verticalArrangement = Arrangement.Top,
                modifier = paddingValues.padding(20.dp)
            ) {
                Spacer(Modifier.size(20.dp))
                Text(task.title)
                Spacer(Modifier.size(20.dp))
                Text(
                    text = task.body,
                    textAlign = TextAlign.Left
                )

                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text("Delete Task")
                    }
                }


            }



        }
    )


}