package com.carlosjimz87.nav3demo.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlosjimz87.nav3demo.domain.model.Screen

@Composable
fun InitScreen(
    state: Screen,
    onLogin: () -> Unit, onSignup: () -> Unit
) {
    LaunchedEffect(state) {
        Log.d("InitScreen", "Restored screen state: $state")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to WanderTrack", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))
        Button(onClick = onLogin) { Text("Login") }
        Spacer(Modifier.height(8.dp))
        Button(onClick = onSignup) { Text("Sign Up") }
    }
}