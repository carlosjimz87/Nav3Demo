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
import com.carlosjimz87.nav3demo.navigation.NavController
import com.carlosjimz87.nav3demo.ui.theme.Nav3DemoTheme
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalMaterial3Api::class)
class NavControllerTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private fun setContentWith(navController: NavController<Screen>) {
        composeTestRule.setContent {
            Nav3DemoTheme {
                Scaffold { innerPadding ->
                    App(controller = navController, innerPadding = innerPadding)
                }
            }
        }
    }

    @Test
    fun testNavigationFlow_fromInit_toLogin_toMap() {
        val navController = FakeNavController<Screen>(initial = Screen.Init)
        setContentWith(navController)

        composeTestRule.onNodeWithText("Welcome to WanderTrack").assertIsDisplayed()
        composeTestRule.onNodeWithText("Login").assertIsDisplayed().performClick()
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithTag("LoginMock")
            .assertIsDisplayed()
            .performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("MapScreen").assertIsDisplayed()
    }

    @Test
    fun testNavigationFlow_fromInit_toSignup_toLogin_toMap_toProfile_andBack_andLogout() {
        val navController = FakeNavController<Screen>(initial = Screen.Init)
        setContentWith(navController)

        composeTestRule.onNodeWithText("Welcome to WanderTrack").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sign Up").assertIsDisplayed().performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Sign Up").assertIsDisplayed()
        composeTestRule.onNodeWithText("Already have an account? Login").assertIsDisplayed()
            .performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Login (Mock)")
            .assertExists()
            .assertIsDisplayed()
            .performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("MapScreen").assertIsDisplayed()
        composeTestRule.onNodeWithText("Go to Profile").assertIsDisplayed().performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Profile Screen").assertIsDisplayed()
        composeTestRule.onNodeWithText("Logout").assertIsDisplayed().performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Welcome to WanderTrack").assertIsDisplayed()
    }

    @Test
    fun testNavigationFlow_fromInit_toSignup_toMap() {
        val navController = FakeNavController<Screen>(initial = Screen.Init)
        setContentWith(navController)

        composeTestRule.onNodeWithText("Welcome to WanderTrack").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sign Up").assertIsDisplayed().performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Sign Up").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sign Up (Mock)").assertIsDisplayed().performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("MapScreen").assertIsDisplayed()
    }

    @Test
    fun testNavigationFlow_fromInit_toLogin_andBack() {
        val navController = FakeNavController<Screen>(initial = Screen.Init)
        setContentWith(navController)

        composeTestRule.onNodeWithText("Welcome to WanderTrack").assertIsDisplayed()
        composeTestRule.onNodeWithText("Login").assertIsDisplayed().performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
        composeTestRule.activityRule.scenario.onActivity {
            navController.replace(Screen.Init)
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Welcome to WanderTrack").assertIsDisplayed()
    }

    @Test
    fun testNavigationFlow_fromInit_toSignup_andBack() {
        val navController = FakeNavController<Screen>(initial = Screen.Init)
        setContentWith(navController)

        composeTestRule.onNodeWithText("Welcome to WanderTrack").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sign Up").assertIsDisplayed().performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Sign Up").assertIsDisplayed()
        composeTestRule.activityRule.scenario.onActivity {
            navController.replace(Screen.Init)
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Welcome to WanderTrack").assertIsDisplayed()
    }

    @Test
    fun testNavigationFlow_ProfileBackNavigation() {
        val navController = FakeNavController<Screen>(initial = Screen.Map())
        navController.navigate(Screen.Profile())
        setContentWith(navController)

        composeTestRule.onNodeWithText("Profile Screen").assertIsDisplayed()

        composeTestRule.activityRule.scenario.onActivity {
            navController.pop()
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("MapScreen").assertIsDisplayed()
    }
}