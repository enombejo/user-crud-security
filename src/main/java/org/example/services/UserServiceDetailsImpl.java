package org.example.services;

import org.example.dao.UserDao;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceDetailsImpl implements UserService {

    private String name;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.add(user);
    }

    
    @Transactional(readOnly = true)
    @Override
    public List<User> listUser() {
        return userDao.listUsers();
    }


    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserByName(s);
        name = user.getName();
        System.out.println(name + "  userSer");
        return user;
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}
