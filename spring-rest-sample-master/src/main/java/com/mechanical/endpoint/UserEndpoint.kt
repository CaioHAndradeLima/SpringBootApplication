package com.mechanical.endpoint

import com.mechanical.cassandraRepository.User
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import com.mechanical.cassandraRepository.UserRepository
import org.springframework.beans.factory.annotation.Autowired


@RestController
@RequestMapping("user")
class UserEndpoint {

    @Autowired
    private lateinit var repository: UserRepository


    @RequestMapping(method = [RequestMethod.GET])
    fun get(): MutableList<User> {
        repository.insert<User>(User("caio", 21))

        return repository.findAll()
    }

}