package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    Category findById(int id);

    List<Category> findCategoriesBySpecies(String species);

    Category save(Category category);
    void deleteById(int id);
}
