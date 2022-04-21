package com.techsystem.extension

import com.google.gson.Gson

val gson = Gson()

fun Any.toJson(): String = gson.toJson(this)