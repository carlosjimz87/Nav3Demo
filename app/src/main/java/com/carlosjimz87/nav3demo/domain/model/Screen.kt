package com.carlosjimz87.nav3demo.domain.model

sealed class Screen {
    data object Init : Screen()
    data object Login : Screen()
    data object Signup : Screen()
    data object Map : Screen()
    data object Profile : Screen()
}