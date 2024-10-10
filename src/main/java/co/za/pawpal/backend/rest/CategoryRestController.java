// CategoryRestController.java
package co.za.pawpal.backend.rest;

import co.za.pawpal.backend.dto.CategoryDto;
import co.za.pawpal.backend.entity.Category;
import co.za.pawpal.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/categories/species/{species}")
    public ResponseEntity<List<CategoryDto>> getCategoriesBySpecies(@PathVariable String species) {
        List<Category> categories = categoryService.findCategoriesBySpecies(species);
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no categories are found
        }
        // Convert List<Category> to List<CategoryDto>
        List<CategoryDto> categoryDtos = categories.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryDtos); // 200 OK with the list of CategoryDto objects
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
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setAnimalType(category.getAnimalType());
        return dto;
    }
}
