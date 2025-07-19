package com.carlosjimz87.nav3demo.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.carlosjimz87.nav3demo.domain.model.Screen
import com.carlosjimz87.nav3demo.ui.screens.DetailScreen
import com.carlosjimz87.nav3demo.ui.screens.HomeScreen

@Composable
fun Nav3App(innerPadding: PaddingValues) {
    val backStack = remember { mutableStateListOf<Screen>(Screen.Home) }
    val currentScreen = backStack.last()

    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
        when (currentScreen) {
            is Screen.Home -> {
                HomeScreen(
                    onItemClick = { itemId -> backStack.add(Screen.Detail(itemId)) }
                )
            }

            is Screen.Detail -> {
                DetailScreen(
                    itemId = currentScreen.itemId,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }
    }
}