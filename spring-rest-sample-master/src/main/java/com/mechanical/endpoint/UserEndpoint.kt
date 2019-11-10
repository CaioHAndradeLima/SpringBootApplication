package com.mechanical.endpoint

import com.mechanical.cassandraRepository.impl.UserImpl
import com.mechanical.cassandraRepository.model.UserCassandraModel
import com.mechanical.cassandraRepository.repository.UserRepository
import com.mechanical.isDebug
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import retrofit2.http.POST
import java.lang.IllegalStateException

@RestController
@RequestMapping("api/user")
class UserEndpoint {

    @Autowired
    private lateinit var repository: UserImpl

    /*
        @RequestMapping(method = [RequestMethod.GET])
        fun get(@RequestBody user : User): ResponseEntity<*> {
            val userSaved = repository.insert(user)

            return ResponseEntity(userSaved, HttpStatus.CREATED)
        }
    */

    @PostMapping
    fun updateUserToLawyer(@RequestBody user: UserCassandraModel) {
        if(!isDebug)
            throw IllegalStateException("error")

        repository.updateUserToLawyer(user)

    }
}