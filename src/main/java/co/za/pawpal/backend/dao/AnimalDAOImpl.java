package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.Animal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimalDAOImpl implements AnimalDAO {

    // Define a field for EntityManager
    private EntityManager entityManager;

    // Constructor-based dependency injection
    @Autowired
    public AnimalDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Animal> findAll() {
        // Create a query to select all animals from the Animal entity
        TypedQuery<Animal> theQuery = entityManager.createQuery("SELECT a FROM Animal a", Animal.class);
        // Execute the query and get the result list
        List<Animal> animals = theQuery.getResultList();
        return animals;
    }

    @Override
    public Animal findById(int id) {
        // Find an Animal by its ID
        Animal theAnimal = entityManager.find(Animal.class, id);
        return theAnimal;
    }

    @Override
    public Animal save(Animal animal) {
        // Merge the given animal object with the current persistent context
        Animal dbAnimal = entityManager.merge(animal);
        return dbAnimal;
    }

    @Override
    public void deleteById(int id) {
        // Find the animal by ID
        Animal theAnimal = entityManager.find(Animal.class, id);
        if (theAnimal != null) {
            // Remove the animal if it existss
            entityManager.remove(theAnimal);
        }
    }

    @Override
    public Animal findByName(String name) {
        // Create a query to find an animal by its name
        TypedQuery<Animal> query = entityManager.createQuery(
                "SELECT a FROM Animal a WHERE a.name = :name", Animal.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}