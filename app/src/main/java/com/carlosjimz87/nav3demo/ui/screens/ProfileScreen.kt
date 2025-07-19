package com.carlosjimz87.nav3demo.ui.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlosjimz87.nav3demo.domain.model.Screen

@Composable
fun ProfileScreen(
    state: Screen,
    onLogout: () -> Unit,
    onBack: () -> Unit
) {

    LaunchedEffect(state) {
        Log.d("MapScreen", "Restored screen state: $state")
    }

    BackHandler(onBack = onBack)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onLogout) { Text("Logout") }
    }
}
