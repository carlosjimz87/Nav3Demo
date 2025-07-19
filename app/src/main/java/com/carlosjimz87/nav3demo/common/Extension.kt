package com.carlosjimz87.nav3demo.common

import android.util.Log
import com.carlosjimz87.nav3demo.domain.model.Screen


fun Screen.logTrans() {
    Log.d("NavApp", "Navigated to: ${this::class.simpleName}")
}