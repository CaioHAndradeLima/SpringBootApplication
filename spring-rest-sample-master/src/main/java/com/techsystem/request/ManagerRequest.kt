package com.techsystem.request

import com.techsystem.extension.gson
import com.techsystem.extension.toJson
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder

inline fun <reified T, Y> managerRequest(json: String?, call: (it: T?) -> Pair<ResponseEntity.BodyBuilder, Y?>): ResponseEntity<*> {
    return managerRequest(json, call, false)
}

inline fun <reified T, Y> managerRequest(json: String?, call: (it: T?) -> Pair<ResponseEntity.BodyBuilder, Y?>, hasSession: Boolean = false): ResponseEntity<*> {
    val user = if(hasSession) {
        provideSession() ?: return ResponseEntity.status(
                HttpStatus.FORBIDDEN
        ).build<Unit>()
    } else {
        null
    }

    val entity: T = if(json != null ) { gson.fromJson(
            json,
            T::class.java) }  else { null as T }

    val pair = call(entity)

    if(pair.second != null) {
        val jsonEncryptedReturn = pair.toJson()
        pair.first.body(jsonEncryptedReturn)
    }

    return pair.first.build<Y>()
}

fun provideSession() = null


fun provideUserAuthenticate(): UserCassandraModel? {

    if(SecurityContextHolder.getContext().authentication != null) {
        return (SecurityContextHolder.getContext().authentication as AuthenticationToken).authenticatedUser.user.user;
    }

    return null;
}
