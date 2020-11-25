package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    void updateUser(User user);
    void deleteUser(long id);

}
