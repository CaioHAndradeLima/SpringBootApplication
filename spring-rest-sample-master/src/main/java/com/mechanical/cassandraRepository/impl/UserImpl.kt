package com.mechanical.cassandraRepository.impl

import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.extensions.isJustNumber
import com.mechanical.cassandraRepository.repository.AddressUserRepository
import com.mechanical.cassandraRepository.repository.UserRepository
import com.mechanical.endpoint.LoginEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserImpl {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var addressUserRepository: AddressUserRepository

    fun getAllUser(loginEntity: LoginEntity): User? {
        val user = getAllUser(loginEntity.emailOrCPF)
                ?: return null

        if(user.user.password != loginEntity.password) return null

        return user
    }


    private fun getAllUser(cpfOrEmail: String): User? {
        val userModel = (if (cpfOrEmail.isJustNumber()) {
            userRepository.findBycpf(cpfOrEmail)
        } else {
            userRepository.findByEmail(cpfOrEmail)
        }) ?: return null

        val addressModel = addressUserRepository.findByIdOrNull(userModel.UUIDAddress)

        return User(userModel, addressModel)
    }


}