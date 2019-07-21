package com.mechanical.core

import com.mechanical.user.DetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.http.HttpServletRequest


class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    internal lateinit var demoAuthenticationProvider: DetailsService

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(demoAuthenticationProvider)
    }


    override fun configure(webSecurity: WebSecurity) {
/*
        webSecurity
                .ignoring() */
                //.antMatchers("/login/**")
                //.antMatchers("/apilogin")


    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        http
                .authorizeRequests()
                .and()
                .antMatcher("/user")
                .addFilterBefore(DetailsService.DemoAuthenticationFilter(), BasicAuthenticationFilter::class.java)
/*
        http.addFilterBefore(
                HeaderUsernamePasswordAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter::class.java
        )
                .authorizeRequests()
                .antMatchers("/login", "/apilogin", "/index.html")
                .denyAll()
*/
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



