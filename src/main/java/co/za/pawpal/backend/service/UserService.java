package co.za.pawpal.backend.service;

import co.za.pawpal.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    User save(User user);

    void deleteByID(int id);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
