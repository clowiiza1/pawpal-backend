package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.AnimalCategory;

import java.util.List;

public interface AnimalCategoryDAO {
    List<AnimalCategory> findAll();
    AnimalCategory findById(int id);
    AnimalCategory save(AnimalCategory animalCategory);
    void deleteById(int id);
}
