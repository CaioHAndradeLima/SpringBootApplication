package com.mechanical.endpoint

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("apilogin")
class LoginEndpoint {

    @GetMapping
    fun login() = ResponseEntity(Data(UUID.randomUUID().toString()),HttpStatus.OK)
}

data class Data(var secretCode : String )