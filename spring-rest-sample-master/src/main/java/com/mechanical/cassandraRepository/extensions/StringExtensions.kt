package com.mechanical.cassandraRepository.extensions

import java.lang.StringBuilder
import java.text.SimpleDateFormat
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

fun String.formatToDate(format: String = "dd/MM/yyyy") : Date {
   return SimpleDateFormat(format).parse(this)
}