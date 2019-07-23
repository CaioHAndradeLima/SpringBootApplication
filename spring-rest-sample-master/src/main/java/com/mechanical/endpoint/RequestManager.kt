package com.mechanical.endpoint

import com.google.gson.Gson
import org.springframework.http.ResponseEntity

import com.mechanical.infix_utils.toJson

val gson = Gson()

inline fun <reified T, Y> managerRequest(jsonEncrypted: String, call: (it: T) -> Pair<ResponseEntity.BodyBuilder, Y?>): ResponseEntity<*> {

    val entity = gson.fromJson(
            desencrypt(jsonEncrypted),
            T::class.java
    )

    val pair = call(entity)

    pair.second?.let {
        val jsonEncrypted = encrypt(it!!.toJson())
        pair.first.body(jsonEncrypted)
    }

    return pair.first.build<Y>()
}

fun desencrypt(jsonEncrypted: String): String {
    return jsonEncrypted
}

fun encrypt(textToEncrypt: String) : String {
    return textToEncrypt
}
