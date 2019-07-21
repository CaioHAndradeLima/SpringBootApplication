package com.mechanical.endpoint

import com.mechanical.cassandraRepository.impl.UserImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import retrofit2.http.Body
import java.util.*

@RestController
@RequestMapping("apilogin")
class LoginEndpoint {

    @Autowired
    lateinit var userImpl: UserImpl

    @PostMapping
    fun login(@Body loginEntity: LoginEntity): ResponseEntity<*> {

        val user = userImpl.getAllUser(loginEntity)
                ?: return ResponseEntity<Any>(HttpStatus.UNPROCESSABLE_ENTITY)

        return ResponseEntity(user,HttpStatus.OK)
    }
/*
    @PostMapping("register")
    fun registerAccount() : ResponseEntity<*> {

    }
*/

    companion object {
        @Bean
        private fun getUserImplementation() = UserImpl()
    }

}

data class LoginEntity(var emailOrCPF: String,
                       var password: String)

data class Data(var secretCode: String)