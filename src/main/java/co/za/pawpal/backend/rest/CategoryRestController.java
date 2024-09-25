package co.za.pawpal.backend.rest;

import co.za.pawpal.backend.entity.Category;
import co.za.pawpal.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{categoryId}")
    public Category getCategory(@PathVariable int categoryId) {
        Category category = categoryService.findById(categoryId);
        if (category == null) {
            throw new RuntimeException("Category ID not found - " + categoryId);
        }
        return category;
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping("/categories")
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }


    @DeleteMapping("/categories/{categoryId}")
    public String deleteCategory(@PathVariable int categoryId) {
        Category category = categoryService.findById(categoryId);
        if (category == null) {
            throw new RuntimeException("Category ID not found - " + categoryId);
        }

        categoryService.deleteById(categoryId);
        return "Deleted category id - " + categoryId;
    }
}
