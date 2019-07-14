package com.mechanical.user;

import com.mechanical.cassandraRepository.User;
import com.mechanical.cassandraRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DetailsService implements UserDetailsService {

    @Autowired
    private UserRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = users.findById(UUID.fromString(username));

        if (!optionalUser.isPresent()){
            throw new UsernameNotFoundException(username + " was not found");
        }

        User user = optionalUser.get();

        String[] array = user.getRoles() == null ? new String[]{} : (String[]) user.getRoles().toArray();

        return new org.springframework.security.core.userdetails.User(
                user.getUuid().toString(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList( array )
        );
    }
}
