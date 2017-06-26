package by.it.milosh.service;

import by.it.milosh.entity.Role;
import by.it.milosh.entity.RoleEnum;
import by.it.milosh.entity.User;
import by.it.milosh.repository.RoleDao;
import by.it.milosh.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @PostConstruct
    public void init() {
        if (userDao.findByUsername("user") == null) {
            User newUser = new User("user", new BCryptPasswordEncoder().encode("pass"), true, true, true, true);
            userDao.save(newUser);

            Role newRole = new Role(RoleEnum.USER.getType());
            roleDao.save(newRole);

            Role role = (Role) roleDao.findOne(1L);
            User user = (User) userDao.findOne(1L);
            user.getRoles().add(role);
            userDao.save(user);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            System.out.println("User: " + username + " not found.");
            throw new UsernameNotFoundException("User not found");
        }
        return userDao.findByUsername(username);
    }
}
