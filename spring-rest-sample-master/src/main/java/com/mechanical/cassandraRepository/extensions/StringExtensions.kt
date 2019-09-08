package com.mechanical.cassandraRepository.extensions

import java.lang.StringBuilder
import java.util.*

fun String.cutSpaces(): String {

    val listStringJson = replace(" ", "")
            .replace("\u0000", "")
            .split("\\s".toRegex())

    val stringBuilder = StringBuilder(listStringJson.size)

    listStringJson.forEach {
        stringBuilder.append(it)
    }

    return stringBuilder.toString()
}

fun String.toUUID() = UUID.fromString(this)