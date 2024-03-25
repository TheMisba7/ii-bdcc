package org.example.springmvcthymeleaf.security;

import org.example.springmvcthymeleaf.dao.UserDao;
import org.example.springmvcthymeleaf.model.User;
import org.example.springmvcthymeleaf.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {
    private final UserDao userDao;

    public CustomUserDetailService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDaoByEmail = userDao.findByEmail(username);
        if (userDaoByEmail == null)
            throw new  UsernameNotFoundException("user with email " + username + "not found.");

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole userRole: userDaoByEmail.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getName()));
        }

        return new org.springframework.security.core.userdetails.User(username, userDaoByEmail.getPassword(), authorities);
    }
}
