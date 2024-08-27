package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDAO {
    List<Role> findAll();

    Role findById(int id);

    Role save(Role role);

    void deleteByID(int id);

    Role findByName(String name);
}
