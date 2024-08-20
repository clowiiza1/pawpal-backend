package co.za.pawpal.backend.service;

import co.za.pawpal.backend.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    User save(User user);

    void deleteByID(int id);
}
