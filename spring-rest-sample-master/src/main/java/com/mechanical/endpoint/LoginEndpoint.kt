package com.mechanical.endpoint

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mechanical.cassandraRepository.WorkerSession
import com.mechanical.cassandraRepository.impl.WorkerImpl
import com.mechanical.security.AuthenticationToken
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
import javax.servlet.http.HttpServletRequest
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("loginapi")
class LoginEndpoint {

    @Autowired
    lateinit var workerImpl: WorkerImpl

    @PostMapping
    fun login(request: HttpServletRequest, @RequestBody loginEntity: LoginEntity): ResponseEntity<*> {

        val user = workerImpl.getAllUser(loginEntity)
                ?: return ResponseEntity<Any>(HttpStatus.UNPROCESSABLE_ENTITY)

        loginEntity.workerSession = user

        val auth = AuthenticationToken(loginEntity)
        auth.isAuthenticated = true
        SecurityContextHolder.getContext().authentication = auth


        val session = request.getSession(true)
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext())

        return ResponseEntity(user.toJson(),HttpStatus.OK)
    }

    @PostMapping("register")
    fun registerAccount(@RequestBody requestEncrypted: String) : ResponseEntity<*> {
        return managerRequest<WorkerSession,Any>(requestEncrypted) { it, user ->

            val isSalved = workerImpl.saveUser(it!!)

            if(!isSalved) {
                return@managerRequest Pair(ResponseEntity.status(HttpStatus.CONFLICT), null)
            }

            return@managerRequest Pair(ResponseEntity.status(HttpStatus.CREATED), null)
        }
    }
}

data class LoginEntity(@SerializedName("emailOrCPF") @NotNull @NotEmpty var emailOrCPF: String,
                       @SerializedName("password") @NotNull @NotEmpty var password: String,
                       @SerializedName("macAddress") @NotNull @NotEmpty var macAddress: String,
                       @SerializedName("keyOfRequests") @NotNull @NotEmpty var keyOfRequests: String) {
    @Expose(serialize = false,deserialize = false) lateinit var workerSession: WorkerSession
}

data class Data(var secretCode: String)