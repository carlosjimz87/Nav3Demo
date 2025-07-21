package com.carlosjimz87.nav3demo

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.carlosjimz87.nav3demo.common.logTrans
import com.carlosjimz87.nav3demo.domain.model.Screen
import com.carlosjimz87.nav3demo.navigation.NavController
import com.carlosjimz87.nav3demo.ui.screens.InitScreen
import com.carlosjimz87.nav3demo.ui.screens.LoginScreen
import com.carlosjimz87.nav3demo.ui.screens.MapScreen
import com.carlosjimz87.nav3demo.ui.screens.ProfileScreen
import com.carlosjimz87.nav3demo.ui.screens.SignupScreen

@Composable
fun App(controller: NavController<Screen>, innerPadding: PaddingValues) {
    val context = LocalContext.current
    val currentScreen by remember { derivedStateOf { controller.backStack.last() } }
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
                onLogin = { controller.navigate(Screen.Login()) },
                onSignup = { controller.navigate(Screen.Signup()) }
            )

            is Screen.Login -> LoginScreen(
                state = screen,
                onLoginSuccess = {
                    controller.replace(Screen.Map())
                },
                onBack = {
                    controller.replace(Screen.Init)
                }
            )

            is Screen.Signup -> SignupScreen(
                state = screen,
                onSignupSuccess = {
                    controller.replace(Screen.Map())
                },
                onBack = {
                    controller.replace(Screen.Init)
                },
                onNavigateToLogin = {
                    controller.replace(Screen.Login())
                }
            )

            is Screen.Map -> MapScreen(
                state = screen,
                onProfileClick = { controller.navigate(Screen.Profile()) },
                onBack = { (context as? Activity)?.finish() }
            )

            is Screen.Profile -> ProfileScreen(
                state = screen,
                onLogout = {
                    controller.replace(Screen.Init)
                },
                onBack = {
                    controller.pop()
                }
            )
        }
    }
}