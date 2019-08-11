package com.mechanical.cassandraRepository.extensions

import java.lang.StringBuilder

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