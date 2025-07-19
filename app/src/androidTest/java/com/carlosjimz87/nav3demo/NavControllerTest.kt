package com.carlosjimz87.nav3demo

import androidx.activity.ComponentActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.carlosjimz87.nav3demo.domain.model.Screen
import com.carlosjimz87.nav3demo.ui.theme.Nav3DemoTheme
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalMaterial3Api::class)
class NavControllerTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testNavigationFlow_fromInit_toLogin_toMap() {
        val navController = FakeNavController<Screen>(initial = Screen.Init)

        composeTestRule.setContent {
            Nav3DemoTheme {
                Scaffold { innerPadding ->
                    App(controller = navController, innerPadding = innerPadding)
                }
            }
        }

        // 1. Should show Init screen
        composeTestRule.onNodeWithText("Welcome to WanderTrack").assertIsDisplayed()

        // 2. Navigate to Login
        composeTestRule.onNodeWithText("Login").performClick()
        composeTestRule.waitForIdle()

        // 3. Should show Login screen
        composeTestRule.onNodeWithText("Login").assertIsDisplayed()

        // 4. Tap Login button (mock)
        composeTestRule.onNodeWithText("Login (Mock)").performClick()
        composeTestRule.waitForIdle()

        // 5. Should show Map screen
        composeTestRule.onNodeWithTag("MapScreen").assertIsDisplayed()
    }
}