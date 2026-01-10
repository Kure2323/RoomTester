package com.polete.roomtester.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.polete.roomtester.data.AppDatabase
import com.polete.roomtester.data.Task
import com.polete.roomtester.screens.AddTaskScreen
import com.polete.roomtester.screens.MainScreen
import com.polete.roomtester.screens.TaskDetailsScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val taskDao = AppDatabase.getDatabase(context = LocalContext.current).taskDao()

    var task: Task? = null


    NavHost(
        navController = navController,
        startDestination = Screens.mainScreen.route
    ) {

        composable(route = Screens.mainScreen.route) {
            MainScreen(taskDao = taskDao, navController = navController)
        }

        composable(route = Screens.addTaskScreen.route) {
            AddTaskScreen(taskDao = taskDao, navController = navController)
        }

        composable(
            // Route sin los argumentos, hazme caso que si lo pones te vas a dar muchos golpes
            route = Screens.taskDetailScreen.route,
            // Declaración de obtención del argumento pasado por navController
            // Cuidado con el NavType
            arguments = listOf(navArgument("taskId") { type = NavType.LongType})
        ) { backStackEntry ->

            //Obtención del id de la task presionada en la pantalla anterior
            val taskId = backStackEntry.arguments!!.getLong("taskId")

            TaskDetailsScreen(
                taskDao = taskDao,
                navController = navController,
                taskId = taskId
            )

        }



    }

}