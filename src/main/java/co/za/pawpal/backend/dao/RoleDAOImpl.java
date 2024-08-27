package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.Role;
import co.za.pawpal.backend.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    //define field for entitymanager
    private EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Role> findAll() {
        TypedQuery<Role> theQuery = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        List<Role> roles = theQuery.getResultList();
        return roles;
    }

    @Override
    public Role findById(int id) {
        Role theRole= entityManager.find(Role.class, id);
        return theRole;
    }

    @Override
    public Role save(Role role) {
       Role dbRole = entityManager.merge(role);
        return dbRole;
    }

    @Override
    public void deleteByID(int id) {
        Role theRole = entityManager.find(Role.class, id);
        entityManager.remove(theRole);
    }

    @Override
    public Role findByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
