package com.mechanical.cassandraRepository.extensions


fun Throwable.newLog() {
    if(isDebug)
        printStackTrace()
}