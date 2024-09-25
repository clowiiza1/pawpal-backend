package co.za.pawpal.backend.rest;

import co.za.pawpal.backend.entity.AnimalCategory;
import co.za.pawpal.backend.service.AnimalCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnimalCategoryRestController {

    private final AnimalCategoryService animalCategoryService;

    @Autowired
    public AnimalCategoryRestController(AnimalCategoryService animalCategoryService) {
        this.animalCategoryService = animalCategoryService;
    }

    @GetMapping("/animal-categories")
    public List<AnimalCategory> findAll() {
        return animalCategoryService.findAll();
    }

    @GetMapping("/animal-categories/{categoryId}")
    public AnimalCategory getAnimalCategory(@PathVariable int categoryId) {
        AnimalCategory animalCategory = animalCategoryService.findById(categoryId);
        if (animalCategory == null) {
            throw new RuntimeException("CategoryID not found - " + categoryId);
        }
        return animalCategory;
    }

    @PostMapping("/animal-categories")
    public AnimalCategory addAnimalCategory(@RequestBody AnimalCategory animalCategory) {
        return animalCategoryService.save(animalCategory);
    }

    @PutMapping("/animal-categories")
    public AnimalCategory updateAnimalCategory(@RequestBody AnimalCategory animalCategory) {
        return animalCategoryService.save(animalCategory);
    }

    @DeleteMapping("/animal-categories/{categoryId}")
    public String deleteAnimalCategory(@PathVariable int categoryId) {
        animalCategoryService.deleteById(categoryId);
        return "Deleted animal category id - " + categoryId;
    }
}
