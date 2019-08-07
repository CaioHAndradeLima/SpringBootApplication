package extensions

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.FileReader


val gson = Gson()

inline fun <reified T> mockJson(nameFile: String): T {
    val localFile = "src/test/resources/$nameFile"
    val reader = JsonReader(FileReader(localFile))
    return gson.fromJson(reader, T::class.java)
}


inline fun <reified T> String.fromJson() : T {
    return gson.fromJson(this, T::class.java)
}