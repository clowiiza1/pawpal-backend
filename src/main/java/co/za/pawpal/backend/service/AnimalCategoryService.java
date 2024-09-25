package co.za.pawpal.backend.service;

import co.za.pawpal.backend.entity.AnimalCategory;

import java.util.List;

public interface AnimalCategoryService {
    List<AnimalCategory> findAll();
    AnimalCategory findById(int id);
    AnimalCategory save(AnimalCategory animalCategory);
    void deleteById(int id);
}
