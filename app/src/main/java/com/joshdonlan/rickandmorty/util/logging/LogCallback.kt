package com.joshdonlan.rickandmorty.util.logging

interface LogCallback {
    fun onLogError(
        var1: String,
        var2: String,
        var3: Throwable?
    )
}
