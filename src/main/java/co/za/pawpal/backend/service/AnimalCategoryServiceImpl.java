package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dao.AnimalCategoryDAO;
import co.za.pawpal.backend.entity.AnimalCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalCategoryServiceImpl implements AnimalCategoryService {

    private final AnimalCategoryDAO animalCategoryDAO;

    @Autowired
    public AnimalCategoryServiceImpl(AnimalCategoryDAO animalCategoryDAO) {
        this.animalCategoryDAO = animalCategoryDAO;
    }

    @Override
    public List<AnimalCategory> findAll() {
        return animalCategoryDAO.findAll();
    }

    @Override
    public AnimalCategory findById(int id) {
        return animalCategoryDAO.findById(id);
    }

    @Override
    public AnimalCategory save(AnimalCategory animalCategory) {
        return animalCategoryDAO.save(animalCategory);
    }

    @Override
    public void deleteById(int id) {
        animalCategoryDAO.deleteById(id);
    }
}
