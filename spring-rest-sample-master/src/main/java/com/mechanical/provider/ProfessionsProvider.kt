package com.mechanical.provider

import com.mechanical.cassandraRepository.model.Work

internal class UnreadableException(exception: Throwable): Exception("not possible read this gson", exception)

private lateinit var professions: MutableList<Work>

fun provideProfessions(): MutableList<Work> {
    if(::professions.isInitialized) {
        return professions
    }

    professions = readJsonFile<Array<Work>>("professions.json").toMutableList()

    return professions
}