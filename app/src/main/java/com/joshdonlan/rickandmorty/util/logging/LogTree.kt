package com.joshdonlan.rickandmorty.util.logging

import android.util.Log
import timber.log.Timber.DebugTree

internal class LogTree(private val logCallback: LogCallback?) : DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        return super.createStackElementTag(element) + ":" + element.lineNumber
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (logCallback != null && priority == Log.ERROR) {
            logCallback.onLogError(tag ?: "Unknown", message, t)
        }
        super.log(priority, tag, message, t)
    }

}