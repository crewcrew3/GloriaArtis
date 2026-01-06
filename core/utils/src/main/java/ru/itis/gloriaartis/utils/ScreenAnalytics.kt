package ru.itis.gloriaartis.utils

import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent

object ScreenAnalytics {

    private val analytics = Firebase.analytics
    const val EVENT = "screen_open"
    const val PARAM = "screen_name"

    fun logScreen(screenName: String) {
        analytics.logEvent(EVENT) {
            param(PARAM, screenName)
        }
    }
}