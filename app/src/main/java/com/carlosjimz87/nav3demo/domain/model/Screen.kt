package com.carlosjimz87.nav3demo.domain.model

sealed class Screen {
    data object Home : Screen()
    data class Detail(val itemId: Int) : Screen()
}