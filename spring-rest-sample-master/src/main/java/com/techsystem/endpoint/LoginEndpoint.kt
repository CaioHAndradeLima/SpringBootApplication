package com.techsystem.endpoint

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.techsystem.request.UserSessionModel
import com.techsystem.security.AuthenticationToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("login")
class LoginEndpoint {

    @PostMapping
    fun loginActive(request: HttpServletRequest, @RequestBody jsonTransaction: String): ResponseEntity<Any>? {

        val auth = AuthenticationToken(UserSessionModel())

        val loginPassword = Gson().fromJson(jsonTransaction, Login::class.java)
        if(auth.credentials == "FKSIFGJIWFIWJIRJWI9I") {
            if(!auth.isAuthenticated) {
                auth.isAuthenticated = true
                SecurityContextHolder.getContext().authentication = auth
            }

            val session = request.getSession(true)
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext())

            return ResponseEntity.status(HttpStatus.OK).build<Any>()
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build<Any>()
    }
}

data class Login(
        @SerializedName("password") val password: String
)