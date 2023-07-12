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
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User updUser) {
        entityManager.merge(updUser);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from User us where us.id=:id").setParameter("id", id).executeUpdate();
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }
}
