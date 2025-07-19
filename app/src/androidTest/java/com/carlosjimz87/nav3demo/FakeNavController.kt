package com.carlosjimz87.nav3demo

import androidx.compose.runtime.mutableStateListOf
import com.carlosjimz87.nav3demo.navigation.NavController

class FakeNavController<T>(initial: T) : NavController<T> {
    private val _backStack = mutableStateListOf(initial)

    override val current: T
        get() = _backStack.last()

    override fun navigate(screen: T) {
        _backStack.add(screen)
    }

    override fun replace(screen: T) {
        _backStack.clear()
        _backStack.add(screen)
    }

    override fun pop() {
        if (_backStack.size > 1) _backStack.removeAt(_backStack.lastIndex)
    }

    override val backStack: List<T>
        get() = _backStack
}