package com.techsystem.security

import com.techsystem.request.UserSessionModel
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    val usingSecurity = true

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        //super.configure(http);
        /* http.authorizeRequests()
                .anyRequest()
                .permitAll(); */


        var yourHttp = http

        if (usingSecurity) {
            yourHttp = http.csrf()
                    .disable()
        }
        val security = yourHttp.authorizeRequests()

        if (usingSecurity) {
            security.antMatchers("/api/**").authenticated()
        }

        security.anyRequest()
                .permitAll()

        if (usingSecurity) {
            security.and()
                    .addFilterBefore(DemoAuthenticationFilter(), BasicAuthenticationFilter::class.java)

        }

    }
}


class AuthenticationToken(internal val authenticatedUser: UserSessionModel) : AbstractAuthenticationToken(Arrays.asList<GrantedAuthority>()) {

    companion object {
        private const val GIZMIN_PASSWORD = "FKSIFGJIWFIWJIRJWI9I"
    }
    override fun getCredentials(): Any {
        //return authenticatedUser.password
        return GIZMIN_PASSWORD
    }

    override fun getPrincipal(): Any {
        return authenticatedUser
    }
}

class DemoAuthenticationFilter : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse, filterChain: FilterChain) {

        val xAuth = request.getHeader("X-Authorization")

        // validate the value in xAuth
        /*
    if(isValid(xAuth) == false){
        throw new SecurityException();
    } */

/*
        // The token is 'valid' so magically get a user id from it
        val id = getUserUUIDFromToken(xAuth)
        // Create our Authentication and let Spring know about it
        val auth = AuthenticationToken(id)
        auth.isAuthenticated = true
        SecurityContextHolder.getContext().authentication = auth
        val session = request.getSession(true)
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext())
*/
        filterChain.doFilter(request, response)
    }

    private fun getUserUUIDFromToken(xAuth: String): UUID {
        return UUID.randomUUID()
    }

}