package com.mechanical.endpoint

import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api/user")
class UserEndpoint {

    @Autowired
    private lateinit var repository: UserRepository

    @RequestMapping(method = [RequestMethod.GET])
    fun get(@RequestBody user : User): ResponseEntity<*> {
        val userSaved = repository.insert(user)

        return ResponseEntity(userSaved, HttpStatus.CREATED)
    }

    @GetMapping(value = ["/{uuid}"] )
    fun get(@PathVariable("uuid") uuid: UUID): ResponseEntity<*> {

        val user = repository.findByIdOrNull(uuid)

        return ResponseEntity( user , HttpStatus.OK )
    }

}