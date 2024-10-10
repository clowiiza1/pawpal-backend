package co.za.pawpal.backend.service;

import co.za.pawpal.backend.entity.Animal;
import co.za.pawpal.backend.entity.Category;

import java.util.List;
import java.util.Optional;

public interface AnimalService {

    List<Animal> findAll();

    Animal findById(int id);

    Animal save(Animal animal);

    void deleteById(int id);

    Optional<Animal> findByName(String name);

    List<Animal> findByTypeAndCategories(String species, List<String> categories);

    List<Category> findCategoriesByAnimalId(int animalId);

    Animal updateAnimalCategories(int animalId, List<Integer> categoryIds);
}
