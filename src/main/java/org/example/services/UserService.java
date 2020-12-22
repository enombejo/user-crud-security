package org.example.services;

import org.example.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(User user);
    List<User> listUser();
    void updateUser(User user);
    void deleteUser(long id);
    User getUserByName(String name);
}
