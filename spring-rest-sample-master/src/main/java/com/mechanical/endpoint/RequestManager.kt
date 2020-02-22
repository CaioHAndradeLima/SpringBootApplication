package com.mechanical.endpoint

import com.google.gson.Gson
import com.mechanical.cassandraRepository.WorkerSession
import org.springframework.http.ResponseEntity

import com.mechanical.infix_utils.toJson
import com.mechanical.provider.WorkerProvider.provideUser
import org.springframework.http.HttpStatus

val gson = Gson()


inline fun <reified T, Y> managerRequest(jsonEncrypted: String, call: (it: T, workerSession: WorkerSession?) -> Pair<ResponseEntity.BodyBuilder, Y?>): ResponseEntity<*> {
    return managerRequest(jsonEncrypted, call, false)
}


inline fun <reified T, Y> managerRequest(jsonEncrypted: String, call: (it: T, workerSession: WorkerSession?) -> Pair<ResponseEntity.BodyBuilder, Y?>, hasSession: Boolean = false): ResponseEntity<*> {
    val user = if(hasSession) {
         provideUser() ?: return ResponseEntity.status(
                HttpStatus.FORBIDDEN
        ).build<Unit>()
    } else {
        null
    }

    val entity = gson.fromJson(
            decrypt(jsonEncrypted),
            T::class.java
    )

    val pair = call(entity, user)

    pair.second?.let { y ->
        val jsonEncryptedReturn = encrypt(y!!.toJson())
        return@managerRequest pair.first.body(jsonEncryptedReturn)
    }

    return pair.first.build<Y>()
}


inline fun <reified Y> managerRequest(call: (it: WorkerSession?) -> Pair<ResponseEntity.BodyBuilder, Y?>): ResponseEntity<*> {
    return managerRequest(call, false)
}

inline fun <reified Y> managerRequest(call: (it: WorkerSession?) -> Pair<ResponseEntity.BodyBuilder, Y?>, hasSession: Boolean = false): ResponseEntity<*> {

    val user = if(hasSession) {
        provideUser() ?: return ResponseEntity.status(
                HttpStatus.FORBIDDEN
        ).build<Unit>()
    } else {
        null
    }

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