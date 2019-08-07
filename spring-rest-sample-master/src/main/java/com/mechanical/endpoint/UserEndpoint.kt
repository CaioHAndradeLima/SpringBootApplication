package com.mechanical.endpoint

import com.mechanical.cassandraRepository.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/user")
class UserEndpoint {

    @Autowired
    private lateinit var repository: UserRepository

    /*
        @RequestMapping(method = [RequestMethod.GET])
        fun get(@RequestBody user : User): ResponseEntity<*> {
            val userSaved = repository.insert(user)

            return ResponseEntity(userSaved, HttpStatus.CREATED)
        }
    */
    @GetMapping(value = ["/{cpf}"])
    fun get(@PathVariable("cpf") cpf: String): ResponseEntity<*> {

        val user = repository.findBycpf(cpf)

        return ResponseEntity(user, HttpStatus.OK)
    }

}