// CategoryRestController.java
package co.za.pawpal.backend.rest;

import co.za.pawpal.backend.dto.CategoryDto;
import co.za.pawpal.backend.entity.Category;
import co.za.pawpal.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService theCategoryService) {
        this.categoryService = theCategoryService;
    }

    @GetMapping("/categories")
    public List<CategoryDto> findAll() {
        return categoryService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories/{categoryId}")
    public CategoryDto getCategory(@PathVariable int categoryId) {
        Category category = categoryService.findById(categoryId);
        if (category == null) {
            throw new RuntimeException("Category ID not found - " + categoryId);
        }
        return convertToDto(category);
    }

    @PostMapping("/categories")
    public CategoryDto addCategory(@RequestBody Category category) {
        return convertToDto(categoryService.save(category));
    }

    @PutMapping("/categories")
    public CategoryDto updateCategory(@RequestBody Category category) {
        return convertToDto(categoryService.save(category));
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

    // Helper method to convert Category to CategoryDto
    private CategoryDto convertToDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
