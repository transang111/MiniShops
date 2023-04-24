package com.sang.minishops.service.imp;

import com.sang.minishops.entity.User;
import com.sang.minishops.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * The type User detail devices.
 */
@Service
public class UserDetailDevices implements UserDetailsService {
    /**
     * The User repository.
     */
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
