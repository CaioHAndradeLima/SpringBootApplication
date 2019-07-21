package com.mechanical.cassandraRepository.extensions

fun Any?.isNotNull() = this != null
fun Any?.isNull() = this == null
