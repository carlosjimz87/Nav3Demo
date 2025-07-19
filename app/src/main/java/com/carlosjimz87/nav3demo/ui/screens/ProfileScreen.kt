package com.carlosjimz87.nav3demo.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlosjimz87.nav3demo.domain.model.Screen

@Composable
fun ProfileScreen(
    state: Screen.Profile,
    backStack: SnapshotStateList<Screen>,
    onLogout: () -> Unit,
    onBack: () -> Unit
) {
    var showLogoutConfirm by rememberSaveable { mutableStateOf(state.showLogoutConfirm) }

    LaunchedEffect(showLogoutConfirm) {
        val index = backStack.lastIndex
        if (index != -1 && backStack[index] is Screen.Profile) {
            backStack[index] = Screen.Profile(showLogoutConfirm = showLogoutConfirm)
        }
    }

    BackHandler(onBack = onBack)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen")
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            showLogoutConfirm = true
            onLogout()
        }) {
            Text("Logout")
        }
    }
}