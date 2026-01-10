package com.polete.roomtester

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.polete.roomtester.navigation.AppNavigation
import com.polete.roomtester.ui.theme.RoomTesterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomTesterTheme {
                AppNavigation()
            }
        }
    }

}