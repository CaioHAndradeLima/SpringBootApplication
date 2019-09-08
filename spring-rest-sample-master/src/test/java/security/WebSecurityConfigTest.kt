package security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
open class WebSecurityConfigTest : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        http.authorizeRequests().anyRequest()
                .permitAll()
    }

}