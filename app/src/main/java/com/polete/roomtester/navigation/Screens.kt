package com.polete.roomtester.navigation

import com.polete.roomtester.data.Task

sealed class Screens(val route: String) {
    object mainScreen: Screens("main_screen")
    object addTaskScreen: Screens("add_task_screen")

    //Forma de pasar como parámetro a otra pantalla un id de la task
    object taskDetailScreen: Screens("task_detail_screen/{taskId}") {
        // Importante poner el tipo del parámetro de entrada
        fun createRoute(taskId: Long) = "task_detail_screen/$taskId"
    }
}
