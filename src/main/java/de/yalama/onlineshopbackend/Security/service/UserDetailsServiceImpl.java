package de.yalama.onlineshopbackend.Security.service;

import de.yalama.onlineshopbackend.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        de.yalama.onlineshopbackend.User.model.User user = this.userService.findByEmail(email);
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
