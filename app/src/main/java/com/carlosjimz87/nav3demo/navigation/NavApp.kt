package com.carlosjimz87.nav3demo.navigation

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.carlosjimz87.nav3demo.domain.model.Screen
import com.carlosjimz87.nav3demo.ui.screens.InitScreen
import com.carlosjimz87.nav3demo.ui.screens.LoginScreen
import com.carlosjimz87.nav3demo.ui.screens.MapScreen
import com.carlosjimz87.nav3demo.ui.screens.ProfileScreen
import com.carlosjimz87.nav3demo.ui.screens.SignupScreen

@Composable
fun Nav3App(innerPadding: PaddingValues) {
    val backStack = remember { mutableStateListOf<Screen>(Screen.Init) }
    val currentScreen = backStack.last()
    val context = LocalContext.current

    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
        when (currentScreen) {
            is Screen.Init -> InitScreen(
                onLogin = { backStack.add(Screen.Login) },
                onSignup = { backStack.add(Screen.Signup) }
            )

            is Screen.Login -> LoginScreen(
                onLoginSuccess = {
                    backStack.clear()
                    backStack.add(Screen.Map)
                },
                onBack = {
                    backStack.clear()
                    backStack.add(Screen.Init)
                }
            )

            is Screen.Signup -> SignupScreen(
                onSignupSuccess = {
                    backStack.clear()
                    backStack.add(Screen.Map)
                },
                onBack = {
                    backStack.clear()
                    backStack.add(Screen.Init)
                },
                onNavigateToLogin = {
                    backStack.clear()
                    backStack.add(Screen.Login)
                }
            )

            is Screen.Map -> MapScreen(
                onProfileClick = { backStack.add(Screen.Profile) },
                onBack = { (context as? Activity)?.finish() }
            )

            is Screen.Profile -> ProfileScreen(
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