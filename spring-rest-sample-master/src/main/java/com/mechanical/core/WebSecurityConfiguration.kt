package com.mechanical.core

/*
import com.mechanical.user.DetailsService
import com.mechanical.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@EnableWebSecurity
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    internal var detailsService: DetailsService? = null

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
    }
}
 */