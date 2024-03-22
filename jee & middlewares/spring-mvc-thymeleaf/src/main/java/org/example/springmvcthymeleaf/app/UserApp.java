package org.example.springmvcthymeleaf.app;

import org.example.springmvcthymeleaf.dao.RoleDao;
import org.example.springmvcthymeleaf.dao.UserDao;
import org.example.springmvcthymeleaf.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserApp extends AbstractApp<UserDao, User>{
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    protected UserApp(UserDao repository, RoleDao roleDao,
                      PasswordEncoder passwordEncoder) {
        super(repository);
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }


    public void create(User user) {
        user.setRoles(roleDao.findAllByNameIn(user.getRoleNames()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        super.add(user);
    }
}
