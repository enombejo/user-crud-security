package org.example.services;

import org.example.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    List<User> listUser();
    void updateUser(User user);
    void deleteUser(long id);
}
