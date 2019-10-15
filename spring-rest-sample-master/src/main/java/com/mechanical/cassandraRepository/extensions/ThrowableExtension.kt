package com.mechanical.cassandraRepository.extensions

import com.mechanical.isDebug


fun Throwable.newLog() {
    if(isDebug)
        printStackTrace()
}