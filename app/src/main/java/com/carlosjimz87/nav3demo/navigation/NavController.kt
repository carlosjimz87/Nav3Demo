package com.carlosjimz87.nav3demo.navigation

interface NavController<T> {
    val current: T
    fun navigate(screen: T)
    fun replace(screen: T)
    fun pop()
    val backStack: List<T>
}