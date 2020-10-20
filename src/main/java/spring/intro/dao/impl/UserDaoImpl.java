package spring.intro.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.intro.dao.UserDao;
import spring.intro.exceptions.DataProcessingException;
import spring.intro.model.User;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert user entity " + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<User> listUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User ", User.class)
                    .getResultList();
        }
    }

    @Override
    public User get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User u where u.id = :userId ", User.class)
                    .setParameter("userId", id)
                    .getSingleResult();
        }
    }
}
