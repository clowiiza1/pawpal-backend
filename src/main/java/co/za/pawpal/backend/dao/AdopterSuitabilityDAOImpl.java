package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.AdopterSuitability;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdopterSuitabilityDAOImpl implements AdopterSuitabilityDAO {

    private final EntityManager entityManager;

    @Autowired
    public AdopterSuitabilityDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<AdopterSuitability> findAll() {
        TypedQuery<AdopterSuitability> query = entityManager.createQuery("SELECT a FROM AdopterSuitability a", AdopterSuitability.class);
        return query.getResultList();
    }

    @Override
    public AdopterSuitability findById(int id) {
        return entityManager.find(AdopterSuitability.class, id);
    }

    @Override
    public AdopterSuitability save(AdopterSuitability adopterSuitability) {
        return entityManager.merge(adopterSuitability);
    }

    @Override
    public void deleteById(int id) {
        AdopterSuitability adopterSuitability = entityManager.find(AdopterSuitability.class, id);
        if (adopterSuitability != null) {
            entityManager.remove(adopterSuitability);
        }
    }
}
