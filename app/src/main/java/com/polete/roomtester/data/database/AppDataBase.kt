package com.polete.roomtester.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.polete.roomtester.data.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // Aquí decimos cómo acceder a los DAOs
    abstract fun taskDao(): DaoTask

    companion object {
        // Volatile asegura que todos los hilos vean la misma instancia
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Función para obtener la base de datos
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {  // solo se crea una vez
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task_database" // nombre del fichero de la base de datos
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}