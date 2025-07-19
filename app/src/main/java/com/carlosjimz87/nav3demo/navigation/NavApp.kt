package com.carlosjimz87.nav3demo.navigation

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.carlosjimz87.nav3demo.common.logTrans
import com.carlosjimz87.nav3demo.domain.model.Screen
import com.carlosjimz87.nav3demo.ui.screens.InitScreen
import com.carlosjimz87.nav3demo.ui.screens.LoginScreen
import com.carlosjimz87.nav3demo.ui.screens.MapScreen
import com.carlosjimz87.nav3demo.ui.screens.ProfileScreen
import com.carlosjimz87.nav3demo.ui.screens.SignupScreen

@Composable
fun Nav3App(innerPadding: PaddingValues) {

    val backStack = rememberSaveable(
        saver = listSaver<SnapshotStateList<Screen>, Screen>(
            save = { it.toList() },
            restore = { it.toMutableStateList() }
        )
    ) {
        mutableStateListOf(Screen.Init)
    }

    val context = LocalContext.current
    val currentScreen = backStack.last()

    LaunchedEffect(currentScreen) {
        currentScreen.logTrans()
    }

    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        when (val screen = currentScreen) {
            is Screen.Init -> InitScreen(
                state = screen,
                onLogin = { backStack.add(Screen.Login()) },
                onSignup = { backStack.add(Screen.Signup()) }
            )

            is Screen.Login -> LoginScreen(
                state = screen,
                backStack = backStack,
                onLoginSuccess = {
                    backStack.clear()
                    backStack.add(Screen.Map())
                },
                onBack = {
                    backStack.clear()
                    backStack.add(Screen.Init)
                }
            )

            is Screen.Signup -> SignupScreen(
                state = screen,
                backStack = backStack,
                onSignupSuccess = {
                    backStack.clear()
                    backStack.add(Screen.Map())
                },
                onBack = {
                    backStack.clear()
                    backStack.add(Screen.Init)
                },
                onNavigateToLogin = {
                    backStack.clear()
                    backStack.add(Screen.Login())
                }
            )

            is Screen.Map -> MapScreen(
                state = screen,
                backStack = backStack,
                onProfileClick = { backStack.add(Screen.Profile()) },
                onBack = { (context as? Activity)?.finish() }
            )

            is Screen.Profile -> ProfileScreen(
                state = screen,
                backStack = backStack,
                onLogout = {
                    backStack.clear()
                    backStack.add(Screen.Init)
                },
                onBack = {
                    backStack.removeLastOrNull()
                }
            )
        }
    }
}