package com.mechanical.endpoint

import com.google.gson.annotations.Expose
import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.impl.UserImpl
import com.mechanical.cassandraRepository.model.UserCassandraModel
import com.mechanical.topic.AuthenticationToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import retrofit2.http.Body
import javax.servlet.http.HttpServletRequest
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("loginapi")
class LoginEndpoint {

    @Autowired
    lateinit var userImpl: UserImpl

    @PostMapping
    fun login(request: HttpServletRequest, @RequestBody loginEntity: LoginEntity): ResponseEntity<*> {

        val user = userImpl.getAllUser(loginEntity)
                ?: return ResponseEntity<Any>(HttpStatus.UNPROCESSABLE_ENTITY)

        loginEntity.user = user

        val auth = AuthenticationToken(loginEntity)
        auth.isAuthenticated = true
        SecurityContextHolder.getContext().authentication = auth


        val session = request.getSession(true)
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext())

        return ResponseEntity(user,HttpStatus.OK)
    }

    @PostMapping("register")
    fun registerAccount(@RequestBody requestEncrypted: String) : ResponseEntity<*> {
        return managerRequest<User,Any>(requestEncrypted) {

            val isSalved = userImpl.saveUser(it)

            if(!isSalved) {
                return@managerRequest Pair(ResponseEntity.status(HttpStatus.CONFLICT), null)
            }

            return@managerRequest Pair(ResponseEntity.status(HttpStatus.CREATED), null)
        }
    }

    companion object {
        @Bean
        private fun getUserImplementation() = UserImpl()
    }

}

data class LoginEntity(@NotNull @NotEmpty var emailOrCPF: String,
                       @NotNull @NotEmpty var password: String,
                       @NotNull @NotEmpty var macAddress: String,
                       @NotNull @NotEmpty var keyOfRequests: String) {
    @Expose(serialize = false,deserialize = false) lateinit var user: User
}

data class Data(var secretCode: String)