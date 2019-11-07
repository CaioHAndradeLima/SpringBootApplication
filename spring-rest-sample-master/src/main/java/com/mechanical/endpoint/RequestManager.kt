package com.mechanical.endpoint

import com.google.gson.Gson
import com.mechanical.cassandraRepository.User
import org.springframework.http.ResponseEntity

import com.mechanical.infix_utils.toJson
import com.mechanical.provider.UserProvider.provideUser
import org.springframework.http.HttpStatus

val gson = Gson()

inline fun <reified T, Y> managerRequest(jsonEncrypted: String, call: (it: T?, user: User) -> Pair<ResponseEntity.BodyBuilder, Y?>): ResponseEntity<*> {

    val user = provideUser() ?: return ResponseEntity.status(
            HttpStatus.FORBIDDEN
    ).build<Unit>()

    val entity = gson.fromJson(
            decrypt(jsonEncrypted),
            T::class.java
    )

    val pair = call(entity, user)

    pair.second?.let {
        val jsonEncrypted = encrypt(it!!.toJson())
        pair.first.body(jsonEncrypted)
    }

    return pair.first.build<Y>()
}

inline fun <reified Y> managerRequest(call: (it: User) -> Pair<ResponseEntity.BodyBuilder, Y?>): ResponseEntity<*> {

    val user = provideUser() ?: return ResponseEntity.status(
            HttpStatus.FORBIDDEN
    ).build<Unit>()

    val pair = call(user)

    pair.second?.let {
        val jsonEncrypted = encrypt(it!!.toJson())
        val response = pair.first.body(jsonEncrypted)
        return response
    }

    return pair.first.build<Y>()
}


fun decrypt(jsonEncrypted: String): String {
    return jsonEncrypted
}

fun encrypt(textToEncrypt: String): String {
    return textToEncrypt
}