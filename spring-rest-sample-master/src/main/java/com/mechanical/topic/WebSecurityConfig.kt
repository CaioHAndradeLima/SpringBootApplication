package com.mechanical.topic

import com.mechanical.endpoint.LoginEntity
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        //super.configure(http);
        /* http.authorizeRequests()
                .anyRequest()
                .permitAll(); */

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(DemoAuthenticationFilter(),
                        BasicAuthenticationFilter::class.java)


    }


}


class AuthenticationToken(val authenticatedUser: LoginEntity) : AbstractAuthenticationToken(Arrays.asList<GrantedAuthority>()) {

    override fun getCredentials(): Any {
        return authenticatedUser.password
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