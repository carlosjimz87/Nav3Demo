package com.carlosjimz87.nav3demo.ui.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlosjimz87.nav3demo.domain.model.Screen

@Composable
fun SignupScreen(
    state: Screen,
    onSignupSuccess: () -> Unit,
    onBack: () -> Unit,
    onNavigateToLogin: () -> Unit
) {

    LaunchedEffect(state) {
        Log.d("SignupScreen", "Restored screen state: $state")
    }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    BackHandler(onBack = onBack)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sign Up")
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") })
        Spacer(Modifier.height(16.dp))
        Button(onClick = onSignupSuccess) { Text("Sign Up (Mock)") }
        Spacer(Modifier.height(8.dp))
        Button(onClick = onSignupSuccess) { Text("Sign Up with Google Tap (Mock)") }
        Spacer(Modifier.height(8.dp))
        TextButton(onClick = onNavigateToLogin) { Text("Already have an account? Login") }
    }
}
