package com.carlosjimz87.nav3demo

import androidx.compose.runtime.mutableStateListOf
import com.carlosjimz87.nav3demo.navigation.NavController

class FakeNavController<T>(initial: T) : NavController<T> {
    private var _stack = mutableStateListOf(initial)
    override val backStack: List<T> get() = _stack
    override val current: T get() = _stack.last()

    override fun navigate(screen: T) {
        _stack.add(screen)
    }

    override fun replace(screen: T) {
        _stack.clear()
        _stack.add(screen)
    }

    override fun pop() {
        if (_stack.size > 1) _stack.removeAt(_stack.lastIndex)
    }
}