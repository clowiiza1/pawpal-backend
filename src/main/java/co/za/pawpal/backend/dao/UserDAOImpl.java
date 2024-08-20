package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
