package com.mechanical.core

import com.mechanical.user.DetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Component
@EnableWebSecurity(debug = true)
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    internal lateinit var detailsService: DetailsService

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService<UserDetailsService>(detailsService)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        http.addFilterBefore(
                HeaderUsernamePasswordAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter::class.java
        )
                .authorizeRequests()
                .antMatchers("/login", "api/login", "/index.html")
                .denyAll()

    }
}

class HeaderUsernamePasswordAuthenticationFilter : UsernamePasswordAuthenticationFilter() {
    /**
     *
     */
    init {
        this.setFilterProcessesUrl("api/*")
        this.setPostOnly(false)
    }

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainPassword(javax.servlet.http.HttpServletRequest)
     */
    override fun obtainPassword(request: HttpServletRequest): String {
        return request.getHeader(this.passwordParameter)
    }

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainUsername(javax.servlet.http.HttpServletRequest)
     */
    override fun obtainUsername(request: HttpServletRequest): String {
        return request.getHeader(this.passwordParameter)
    }

}
