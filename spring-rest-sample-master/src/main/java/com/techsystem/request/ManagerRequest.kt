package com.techsystem.request

import com.techsystem.extension.gson
import com.techsystem.extension.toJson
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity



inline fun <reified T, Y> managerRequest(jsonEncrypted: String?, call: (it: T?) -> Pair<ResponseEntity.BodyBuilder, Y?>): ResponseEntity<*> {
    return managerRequest(jsonEncrypted, call, false)
}

inline fun <reified T, Y> managerRequest(jsonEncrypted: String?, call: (it: T?) -> Pair<ResponseEntity.BodyBuilder, Y?>, hasSession: Boolean = false): ResponseEntity<*> {
    val user = if(hasSession) {
        provideSession() ?: return ResponseEntity.status(
                HttpStatus.FORBIDDEN
        ).build<Unit>()
    } else {
        null
    }

    val entity: T = if(jsonEncrypted != null ) { gson.fromJson(
            decrypt(jsonEncrypted),
            T::class.java) }  else { null as T }

    val pair = call(entity)

    if(pair.second != null) {
        val jsonEncryptedReturn = encrypt(pair.toJson())
        pair.first.body(jsonEncryptedReturn)
    }

    return pair.first.build<Y>()
}

fun decrypt(jsonEncrypted: String): String {
    return jsonEncrypted
}

fun encrypt(textToEncrypt: String): String {
    return textToEncrypt
}

fun provideSession() = null