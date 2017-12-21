package com.align.inventory.security;

import com.align.inventory.model.Principal;
import com.align.inventory.repository.PrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static java.text.MessageFormat.format;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 21/12/17.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private GrantedAuthority ROLE_USER = new SimpleGrantedAuthority("ROLE_USER");
    private GrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");

    private Collection<GrantedAuthority> userAuthorities = Arrays.asList(ROLE_USER);
    private Collection<GrantedAuthority> adminAuthorities = Arrays.asList(ROLE_USER, ROLE_ADMIN);


    @Autowired
    private PrincipalRepository principalRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.of(principalRepository.findOne(username))
                .map(this::createUser)
                .orElseThrow(() -> new UsernameNotFoundException(
                        format("User with username {0} is not found", username)));
    }

    private UserDetails createUser(Principal principal) {
        Collection<GrantedAuthority> authorities = principal.isAdmin() ? adminAuthorities :userAuthorities;
        return new User(principal.getUser(), principal.getPassword(), authorities);
    }

}
