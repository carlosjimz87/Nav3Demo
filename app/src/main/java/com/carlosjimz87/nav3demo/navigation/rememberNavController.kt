package com.carlosjimz87.nav3demo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import com.carlosjimz87.nav3demo.domain.model.Screen

@Composable
fun rememberNavController(
    initial: Screen = Screen.Init
): NavController<Screen> {
    val stack = rememberSaveable(
        saver = listSaver(
            save = { it.toList() },
            restore = { it.toMutableStateList() }
        )
    ) {
        mutableStateListOf(initial)
    }

    return remember(stack) {
        object : NavController<Screen> {
            override val current: Screen
                get() = stack.last()

            override fun navigate(screen: Screen) {
                stack.add(screen)
            }

            override fun replace(screen: Screen) {
                stack.clear()
                stack.add(screen)
            }

            override fun pop() {
                if (stack.size > 1) stack.removeAt(stack.lastIndex)
            }

            override val backStack: List<Screen>
                get() = stack
        }
    }
}