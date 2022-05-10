package com.techsystem.request

import com.techsystem.extension.gson
import com.techsystem.extension.toJson
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

inline fun <reified T, Y> managerRequest(json: String?, call: (it: T?) -> Pair<ResponseEntity.BodyBuilder, Y?>): ResponseEntity<out Any> {
    val entity: T = if (json != null) {
        gson.fromJson(
                json,
                T::class.java)
    } else {
        null as T
    }

    val pair = call(entity)

    if (pair.second != null) {
        return pair.first.body(pair.second!!.toJson())
    }


    return pair.first.build()
}

fun hasSession() = provideUserAuthenticate() != null


fun provideUserAuthenticate(): UserSessionModel? {

    return UserSessionModel()
    /*
    if(SecurityContextHolder.getContext().authentication != null) {
        return (SecurityContextHolder.getContext().authentication as AuthenticationToken).authenticatedUser
    }

    return null
     */
}

class UserSessionModel