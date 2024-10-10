package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.Animal;
import co.za.pawpal.backend.entity.Category;
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

    @Override
    public List<Animal> findByTypeAndCategories(String species, List<String> categories) {
        // Check if categories list is not empty
        if (categories == null || categories.isEmpty()) {
            // If no categories are provided, return animals only filtered by type and status
            return entityManager.createQuery(
                            "SELECT a FROM Animal a WHERE a.species = :animalType AND a.status = 'Available'", Animal.class)
                    .setParameter("animalType", species)
                    .getResultList();
        }

        // If categories are provided, use the advanced query
        TypedQuery<Animal> query = entityManager.createQuery(
                "SELECT a FROM Animal a " +
                        "JOIN a.categories c " +
                        "WHERE a.species = :animalType " +
                        "AND a.status = 'Available' " + // Add condition for available status
                        "AND c.name IN :categories " +
                        "GROUP BY a " +
                        "HAVING COUNT(DISTINCT c.name) = :categoryCount",
                Animal.class);

        query.setParameter("animalType", species);
        query.setParameter("categories", categories);
        query.setParameter("categoryCount", categories.size());

        return query.getResultList();
    }

    @Override
    public List<Category> findCategoriesByAnimalId(int animalId) {
        // Create a query to get categories related to the animal by animalId
        TypedQuery<Category> query = entityManager.createQuery(
                "SELECT c FROM Category c " +
                        "JOIN c.animals a " +
                        "WHERE a.id = :animalId", Category.class);

        // Set the parameter for animalId
        query.setParameter("animalId", animalId);

        // Execute the query and return the list of categories
        return query.getResultList();
    }

    @Override
    public Animal updateAnimalCategories(int animalId, List<Integer> categoryIds) {
        Animal animal = entityManager.find(Animal.class, animalId);
        if (animal == null) {
            throw new RuntimeException("Animal not found with ID - " + animalId);
        }

        // Fetch the categories based on the provided category IDs
        List<Category> categories = entityManager.createQuery("SELECT c FROM Category c WHERE c.id IN :categoryIds", Category.class)
                .setParameter("categoryIds", categoryIds)
                .getResultList();

        // Set the new categories to the animal
        animal.setCategories(categories);

        // Merge to save the updated animal
        return entityManager.merge(animal);
    }


}
