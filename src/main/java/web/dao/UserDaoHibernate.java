package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import java.util.List;

@Component
public class UserDaoHibernate implements UserDao {

   private final SessionFactory sessionFactory;

   @Autowired
    public UserDaoHibernate(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
    @Transactional(readOnly = true)
    public List<User> getUserList() {
        return sessionFactory.getCurrentSession().createQuery("select u from User u", User.class).getResultList();
    }
    @Transactional(readOnly = true)
    public User getUserById(final int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Transactional
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    public void update(int id, User user) {
        User changedUser=getUserById(id);
        changedUser.setName(user.getName());
        changedUser.setAge(user.getAge());
    }

    @Transactional
    public void delete(int id) {
        sessionFactory.getCurrentSession().remove(getUserById(id));
    }
}
