package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dao.RoleDAO;
import co.za.pawpal.backend.dao.UserDAO;
import co.za.pawpal.backend.entity.Role;
import co.za.pawpal.backend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDAO roleDAO;


    @Autowired
    public RoleServiceImpl(RoleDAO theRoleDAO) {
        this.roleDAO = theRoleDAO;
    }

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public Role findById(int id) {
        Optional<Role> result = Optional.ofNullable(roleDAO.findById(id));
        Role theRole = null;

        if (result.isPresent()) {
            theRole = result.get();
        }
        else {
            //if we didnt find employee by id
            throw new RuntimeException("Did not find Role with id: " + id);
        }
        return theRole;

    }

    @Transactional
    @Override
    public Role save(Role role) {
        return roleDAO.save(role);
    }

    @Transactional
    @Override
    public void deleteByID(int id) {
        roleDAO.deleteByID(id);
    }

    @Override
    public Role findByName(String name) {
       return roleDAO.findByName(name);
    }
}
