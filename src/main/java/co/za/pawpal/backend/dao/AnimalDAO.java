package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.Animal;
import co.za.pawpal.backend.entity.Category;

import java.util.List;

public interface AnimalDAO {
    List<Animal> findAll();

    Animal findById(int id);

    Animal save(Animal animal);

    void deleteById(int id);

    Animal findByName(String name);

    List<Animal> findByTypeAndCategories(String species, List<String> categories);


    List<Category> findCategoriesByAnimalId(int animalId);

    Animal updateAnimalCategories(int animalId, List<Integer> categoryIds);
}
