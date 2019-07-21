package com.mechanical.topic;

import com.mechanical.cassandraRepository.model.UserCassandraModel;
import com.mechanical.cassandraRepository.repository.UserRepository;
import com.mechanical.user.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Create 2 users for demo
    // Secure the endpoins with HTTP Basic authentication

    @Autowired
    private AuthProviderService authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        /* http.authorizeRequests()
                .anyRequest()
                .permitAll(); */

        http.authorizeRequests()
                .anyRequest()
                .authenticated();
        System.out.println("AQUI");
        /*.and()
        .addFilterBefore(new DetailsService.DemoAuthenticationFilter());*/

    }


    @Component
    public class AuthProviderService implements AuthenticationProvider {

        @Autowired
        private UserRepository userRepository;

        @Override
        public Authentication authenticate(Authentication auth) throws AuthenticationException {
            String login = auth.getName();
            String password = auth.getCredentials().toString();

            // Defina suas regras para realizar a autenticação

            Optional<UserCassandraModel>optionalUser = userRepository.findById(UUID.fromString(login));

            if(!optionalUser.isPresent()) {
                throw new UsernameNotFoundException("not found");
            }

            UserCassandraModel userCassandraModel = optionalUser.get();

            if(!userCassandraModel.getPassword().equals(password)) {
                throw new BadCredentialsException("not found");
            }

            Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
            return new UsernamePasswordAuthenticationToken(login, password, authorities);
        }

        @Override
        public boolean supports(Class<?> auth) {
            return auth.equals(UsernamePasswordAuthenticationToken.class);
        }
    }
}
