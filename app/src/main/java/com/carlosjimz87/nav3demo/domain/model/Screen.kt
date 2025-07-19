package com.carlosjimz87.nav3demo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Screen : Parcelable {

    @Parcelize
    data object Init : Screen()

    @Parcelize
    data class Login(val email: String = "") : Screen()

    @Parcelize
    data class Signup(val referrer: String? = null) : Screen()

    @Parcelize
    data class Map(val userId: String = "") : Screen()

    @Parcelize
    data class Profile(val showLogoutConfirm: Boolean = false) : Screen()
}