# 🚀 Navigation 3 Demo with Jetpack Compose

This project showcases a lightweight and testable navigation system in Jetpack Compose using a custom `NavController<Screen>` implementation. It's inspired by the concept of Navigation 3 — using declarative and state-driven navigation, without relying on the official Navigation component.

---

## 🧱 Architecture

The app uses a **declarative navigation model** powered by:

- ✅ `NavController<Screen>` — your custom navigation state holder
- ✅ `Screen` sealed class — defines the navigation graph
- ✅ `rememberNavController()` — factory to hold controller state in composition
- ✅ Jetpack Compose (Material 3)
- ✅ State-driven rendering in `App()` using `when(screen)`
- ✅ Fully testable navigation flows with `FakeNavController`

---

## 📁 Project Structure

```plaintext
app/
├── domain/
│   └── model/Screen.kt              # All screens represented as sealed classes
│
├── navigation/
│   ├── NavController.kt             # Interface for custom NavController
│   └── rememberNavController.kt     # Composable NavController factory
│
├── ui/
│   ├── screens/
│   │   ├── InitScreen.kt
│   │   ├── LoginScreen.kt
│   │   ├── SignupScreen.kt
│   │   ├── MapScreen.kt
│   │   └── ProfileScreen.kt
│   ├── theme/                       # Material 3 theme setup
│   └── App.kt                       # Root Composable using state-based navigation
│
├── MainActivity.kt
└── AndroidManifest.xml

androidTest/
└── NavControllerTest.kt             # UI tests for all navigation flows using FakeNavController

test/
└── FakeNavController.kt             # Custom test implementation for NavController
```

---

## 🧪 Tests

All navigation flows are covered by integration tests using `FakeNavController`. This includes:

- ✅ Navigating from Init → Login → Map
- ✅ Full round-trip: Init → Signup → Login → Map → Profile → Logout
- ✅ Back navigation with `pop()`
- ✅ Replacing screens from anywhere in the flow

To run the tests:

```bash
./gradlew connectedAndroidTest
```

---

## 🧭 Why Navigation 3?

Navigation 3 is an idea that embraces:

- **State-based navigation** instead of imperative `NavHostController`
- **Testability**: easily inject fake controllers and test any flow
- **Separation of concerns**: no `NavGraph` clutter, just sealed classes and simple `when` logic

---

## 🛠 How It Works

- You define your screens using `sealed class Screen`
- The `NavController<Screen>` holds the current stack
- The UI uses `when(screen)` to render based on the current screen
- Transitions happen by calling `controller.navigate(...)`, `pop()` or `replace(...)`
- Screens receive navigation callbacks (e.g., `onBack`, `onLoginSuccess`) as lambdas

---

## 🔧 Setup

To use this pattern in your own project:

1. Copy `NavController.kt` and `rememberNavController.kt`
2. Define your own `Screen` sealed class
3. Create `App()` composable with:

```kotlin
val controller = rememberNavController<Screen>()
val screen = controller.backStack.last()
when (screen) {
    is Screen.X -> ScreenX(...)
}
```

4. Inject a `FakeNavController` in tests for full control

---


## 📝 License

MIT License — use it freely and adapt to your needs.