package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import web.model.User;
import java.util.List;


public class UserDaoImpl implements UserDao {

   private final JdbcTemplate jdbcTemplate;

   @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUserList() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public User getUserById(final int id) {
//        return jdbcTemplate.query("SELECT * FROM users WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users (name, age) VALUES(?, ?)", user.getName(), user.getAge());
    }

    public void update(int id, User user) {
        jdbcTemplate.update("UPDATE users SET name=?, age=? WHERE id=?", user.getName(), user.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }
}
