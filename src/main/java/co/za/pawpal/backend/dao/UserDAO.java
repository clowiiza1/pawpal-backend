package co.za.pawpal.backend.dao;


import co.za.pawpal.backend.entity.Role;
import co.za.pawpal.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> findAll();

    User findById(int id);

    User save(User user);

    void deleteByID(int id);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
