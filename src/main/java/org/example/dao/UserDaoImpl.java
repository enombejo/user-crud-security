package org.example.dao;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public void add(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        user.getRoles().forEach(n -> em.persist(n));
        em.getTransaction().commit();
    }

    @Override
    public List<User> listUsers() {
        return entityManagerFactory.createEntityManager().createQuery("SELECT user FROM User user").getResultList();
    }

    @Override
    public void updateUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User userBD =  em.find(User.class, user.getId());
        userBD.getRoles().forEach(role -> em.remove(role));
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public void deleteUser(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.remove(user);
        em.getTransaction().commit();
    }

    @Override
    public User getUserByName(String name) {
        User user = (User) entityManagerFactory.createEntityManager()
                .createQuery("FROM User WHERE name = :name")
                .setParameter("name", name)
                .getSingleResult();
        return user;
    }

}
