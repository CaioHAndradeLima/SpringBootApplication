package com.mechanical.user;

import com.mechanical.cassandraRepository.User;
import com.mechanical.cassandraRepository.model.UserCassandraModel;
import com.mechanical.cassandraRepository.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Component
public class DetailsService implements AuthenticationProvider {

    // This would be a JPA repository to snag your user entities
    private final UserRepository userRepository;

    @Autowired
    public DetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        DemoAuthenticationToken demoAuthentication = (DemoAuthenticationToken) authentication;
        Optional<UserCassandraModel> optionalUser = userRepository.findById(demoAuthentication.getUUID());

        if (!optionalUser.isPresent()) {
            throw new AuthException("user not found");
        }
/*
        return optionalUser.get();
        */
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return DemoAuthenticationToken.class.isAssignableFrom(authentication);
    }


    static class DemoAuthenticationToken extends AbstractAuthenticationToken {

        private static final long serialVersionUID = -1949976839306453197L;
        private User authenticatedUser;
        private UUID uuid;

        public DemoAuthenticationToken(UUID uuid) {
            super(Arrays.asList());
            this.uuid = uuid;
        }

        public DemoAuthenticationToken(Collection<? extends GrantedAuthority> authorities, User authenticatedUser, UUID uuid) {
            super(authorities);
            this.uuid = uuid;
            this.authenticatedUser = authenticatedUser;
        }

        @Override
        public Object getCredentials() {
            //return authenticatedUser.getPassword();
            return null;
        }

        @Override
        public Object getPrincipal() {
            return authenticatedUser;
        }

        @NotNull
        public UUID getUUID() {
            return uuid;
        }

    }


    class AuthException extends AuthenticationException {
        public AuthException(String msg) {
            super(msg);
        }
    }


    public static class DemoAuthenticationFilter extends OncePerRequestFilter {

        public DemoAuthenticationFilter() {
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            String xAuth = request.getHeader("X-Authorization");

            // validate the value in xAuth
        /*
        if(isValid(xAuth) == false){
            throw new SecurityException();
        } */

            // The token is 'valid' so magically get a user id from it
            UUID id = getUserUUIDFromToken(xAuth);

            // Create our Authentication and let Spring know about it
            Authentication auth = new DemoAuthenticationToken(id);
            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);
        }

        private UUID getUserUUIDFromToken(String xAuth) {
            return UUID.randomUUID();
        }

    }

}



