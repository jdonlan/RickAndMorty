package com.joshdonlan.rickandmorty.util

import com.joshdonlan.rickandmorty.util.logging.LogCallback
import com.joshdonlan.rickandmorty.util.logging.LogTree
import com.joshdonlan.rickandmorty.util.logging.NonFatalException
import timber.log.Timber

object MortyLog {

    private val logTree: LogTree by lazy { LogTree(logCallback) }
    private var logCallback: LogCallback? = null

    @JvmStatic
    fun v(message: String, vararg args: Any?) {
        Timber.v(message, args)
    }

    @JvmStatic
    fun v(t: Throwable?, message: String, vararg args: Any?) {
        Timber.v(t, message, args)
    }

    @JvmStatic
    fun v(t: Throwable?) {
        Timber.v(t)
    }

    @JvmStatic
    fun d(message: String, vararg args: Any?) {
        Timber.d(message, args)
    }

    @JvmStatic
    fun d(t: Throwable?, message: String, vararg args: Any?) {
        Timber.d(t, message, args)
    }

    @JvmStatic
    fun d(t: Throwable?) {
        Timber.d(t)
    }

    @JvmStatic
    fun i(message: String, vararg args: Any?) {
        Timber.i(message, args)
    }

    @JvmStatic
    fun i(t: Throwable?, message: String, vararg args: Any?) {
        Timber.i(t, message, args)
    }

    @JvmStatic
    fun i(t: Throwable?) {
        Timber.i(t)
    }

    @JvmStatic
    fun w(message: String, vararg args: Any?) {
        Timber.w(message, args)
    }

    @JvmStatic
    fun w(t: Throwable?, message: String, vararg args: Any?) {
        Timber.w(t, message, args)
    }

    @JvmStatic
    fun w(t: Throwable?) {
        Timber.w(t)
    }

    @JvmStatic
    fun e(message: String, vararg args: Any?) {
        val formattedMessage = String.format(message, *args)
        Timber.e(NonFatalException(formattedMessage))
    }

    @JvmStatic
    fun e(t: Throwable?, message: String, vararg args: Any?) {
        Timber.e(t, message, args)
    }

    @JvmStatic
    fun e(t: Throwable?) {
        Timber.e(t)
    }

    @JvmStatic
    fun wtf(message: String, vararg args: Any?) {
        Timber.wtf(message, args)
    }

    @JvmStatic
    fun wtf(t: Throwable?, message: String, vararg args: Any?) {
        Timber.wtf(t, message, args)
    }

    @JvmStatic
    fun wtf(t: Throwable?) {
        Timber.wtf(t)
    }

    @JvmStatic
    fun log(priority: Int, message: String, vararg args: Any?) {
        Timber.log(priority, message, args)
    }

    @JvmStatic
    fun log(priority: Int, t: Throwable?, message: String, vararg args: Any?) {
        Timber.log(priority, t, message, args)
    }

    @JvmStatic
    fun log(priority: Int, t: Throwable?) {
        Timber.log(priority, t)
    }

    @JvmStatic
    fun init(logCallback: LogCallback? = null) {
        this.logCallback = logCallback
        Timber.plant(logTree)
    }
}
