package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dao.UserDAO;
import co.za.pawpal.backend.entity.Role;
import co.za.pawpal.backend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;


    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return userDAO.findByUsername(authentication.getName());
        }

        return null; // or throw an exception, depending on your needs
    }

    @Autowired
    public UserServiceImpl(UserDAO theUserDAO) {
        this.userDAO = theUserDAO;

    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(int id) {
        Optional<User> result = Optional.ofNullable(userDAO.findById(id));
        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            //if we didnt find employee by id
            throw new RuntimeException("Did not find User with id: " + id);
        }
        return theUser;

    }

    @Transactional
    @Override
    public User save(User user) {

        return userDAO.save(user);
    }

    @Transactional
    @Override
    public User update(User user) {
        // Find the existing user using the user ID
        User existingUser = userDAO.findById(user.getId());

        if (existingUser == null) {
            throw new RuntimeException("User not found with id: " + user.getId());
        }

        // Update only allowed fields (excluding username)
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setAge(user.getAge());
        existingUser.setRoles(user.getRoles());

        return userDAO.save(existingUser);  // Save updated user via UserDAO
    }


    @Transactional
    @Override
    public void deleteByID(int id) {
        userDAO.deleteByID(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userDAO.existsByUsername(username);
    }

    @Override
    public List<Role> findRoles(){
        // Fetch the user from the database using the DAO
       return getCurrentUser().get().getRoles();
    }


}
