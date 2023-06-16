package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> listUsers();
    void add(User user);
    void update(User user, Long id);
    void delete(Long id);

    User getById(Long id);

}
