package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoHibernate implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public List<User> getUserList() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Transactional(readOnly = true)
    public User getUserById(final int id) {
        return null;
//        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
//        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    public void update(int id, User user) {
//        User changedUser=getUserById(id);
//        changedUser.setName(user.getName());
//        changedUser.setAge(user.getAge());
//        sessionFactory.getCurrentSession().update(changedUser);
    }

    @Transactional
    public void delete(int id) {
//        sessionFactory.getCurrentSession().remove(getUserById(id));
    }
}
