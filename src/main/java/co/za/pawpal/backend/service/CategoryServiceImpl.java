package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dao.CategoryDAO;
import co.za.pawpal.backend.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO theCategoryDAO) {
        this.categoryDAO = theCategoryDAO;
    }

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDAO.findById(id);
    }

    @Transactional
    @Override
    public Category save(Category category) {
        return categoryDAO.save(category);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        categoryDAO.deleteById(id);
    }
}
