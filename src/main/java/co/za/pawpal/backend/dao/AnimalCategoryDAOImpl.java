package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.AnimalCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimalCategoryDAOImpl implements AnimalCategoryDAO {

    private EntityManager entityManager;

    @Autowired
    public AnimalCategoryDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<AnimalCategory> findAll() {
        TypedQuery<AnimalCategory> query = entityManager.createQuery("SELECT ac FROM AnimalCategory ac", AnimalCategory.class);
        return query.getResultList();
    }

    @Override
    public AnimalCategory findById(int id) {
        return entityManager.find(AnimalCategory.class, id);
    }

    @Override
    public AnimalCategory save(AnimalCategory animalCategory) {
        return entityManager.merge(animalCategory);
    }

    @Override
    public void deleteById(int id) {
        AnimalCategory category = findById(id);
        if (category != null) {
            entityManager.remove(category);
        }
    }
}
