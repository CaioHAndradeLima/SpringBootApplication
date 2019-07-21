package com.mechanical.cassandraRepository.extensions

fun String.isJustNumber() : Boolean = toDoubleOrNull().isNotNull()