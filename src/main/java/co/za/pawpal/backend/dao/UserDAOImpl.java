package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.Role;
import co.za.pawpal.backend.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    //define field for entitymanager
    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    //Finding all users
    @Override
    public List<User> findAll() {
        TypedQuery<User> theQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
        List<User> users = theQuery.getResultList();
        return users;
    }

    //Finding user by id
    @Override
    public User findById(int id) {
        User theUser= entityManager.find(User.class, id);
        return theUser;

    }

    //Saving a user in the database
    @Override
    public User save(User user) {
        User dbUser = entityManager.merge(user);
        return dbUser;

    }

    //Deleting a user by id
    @Override
    public void deleteByID(int id) {
        User theUser = entityManager.find(User.class, id);
        entityManager.remove(theUser);

    }

    @Override
    public Boolean existsByUsername(String username) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class);
        query.setParameter("username", username);
        Long count = query.getSingleResult();
        return count > 0;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getResultList().stream().findFirst();
    }
}
