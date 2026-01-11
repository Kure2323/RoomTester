package com.polete.roomtester.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.polete.roomtester.data.database.AppDatabase
import com.polete.roomtester.data.repository.TaskRepository
import com.polete.roomtester.ui.screens.AddTaskScreen
import com.polete.roomtester.ui.screens.MainScreen
import com.polete.roomtester.ui.screens.TaskDetailsScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val taskDao = AppDatabase.getDatabase(context)
    val repo = TaskRepository(taskDao.taskDao())


    NavHost(
        navController = navController,
        startDestination = Screens.mainScreen.route
    ) {

        composable(route = Screens.mainScreen.route) {
            MainScreen(repository = repo, navController = navController)
        }

        composable(route = Screens.addTaskScreen.route) {
            AddTaskScreen(repository = repo, navController = navController)
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
                repository = repo,
                navController = navController,
                taskId = taskId
            )

        }



    }

}