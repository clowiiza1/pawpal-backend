package co.za.pawpal.backend.service;

import co.za.pawpal.backend.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findById(int id);

    Role save(Role role);

    void deleteByID(int id);

    Role findByName(String name);
}
