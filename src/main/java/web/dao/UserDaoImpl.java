package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> listUsers() {
        List<User> usersList = entityManager.createQuery("Select user from User user", User.class)
                      .getResultList();

        return usersList;
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User updUser, Long id) {
        User userToBeUpd = getById(id);
        userToBeUpd.setFirstName(updUser.getFirstName());
        userToBeUpd.setLastName(updUser.getLastName());
        userToBeUpd.setEmail(updUser.getEmail());

        entityManager.merge(userToBeUpd);
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getById(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.detach(user);
        return user;
    }
}
