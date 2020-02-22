package com.mechanical.provider

import com.google.gson.stream.JsonReader
import java.io.FileReader

inline fun <reified T> readJsonFile(nameFile: String): T {
    val localFile = "src/main/resources/$nameFile"
    val reader = JsonReader(FileReader(localFile))
    return provideGson().fromJson(reader, T::class.java)
}