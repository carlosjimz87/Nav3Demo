package com.carlosjimz87.nav3demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.carlosjimz87.nav3demo.domain.model.Screen
import com.carlosjimz87.nav3demo.navigation.rememberNavController
import com.carlosjimz87.nav3demo.ui.theme.Nav3DemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController(Screen.Init)

            Nav3DemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(controller = navController, innerPadding = innerPadding)
                }
            }
        }
    }
}