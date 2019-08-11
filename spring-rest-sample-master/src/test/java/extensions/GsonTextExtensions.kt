package extensions

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.FileReader
import java.io.BufferedReader




val gson = Gson()

inline fun <reified T> mockJson(nameFile: String): T {
    val localFile = "src/test/resources/$nameFile"
    val reader = JsonReader(FileReader(localFile))
    return gson.fromJson(reader, T::class.java)
}


inline fun <reified T> String.fromJson() : T {
    return gson.fromJson(this, T::class.java)
}

fun getJson(fileName: String) : String {
    val localFile = "src/test/resources/$fileName"

    val reader = BufferedReader(FileReader(localFile))
    val stringBuilder = StringBuilder()
    var buffer = CharArray(10)

    while (reader.read(buffer) != -1) {
        stringBuilder.append(String(buffer))
        buffer = CharArray(10)
    }
    reader.close()

    return stringBuilder.toString()
}