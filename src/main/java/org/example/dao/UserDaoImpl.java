package org.example.dao;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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
        em.getTransaction().commit();
        //em.close();
    }

    @Override
    public List<User> listUsers() {
        return entityManagerFactory.createEntityManager().createQuery("SELECT user FROM User user").getResultList();
    }

    @Override
    public void updateUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("update User set firstName = :firstName, lastName = :lastName, email = :email where id = :id")
                .setParameter("id", user.getId())
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastName", user.getLastName())
                .setParameter("email", user.getEmail())
                .executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public void deleteUser(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, new Long(id));
        em.remove(user);
        em.getTransaction().commit();
    }
}
