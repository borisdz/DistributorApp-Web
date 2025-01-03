package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.CategoryDto;
import mk.ukim.finki.db.distributorapp.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesDto() {
        List<CategoryDto> categoryDtos = this.categoryService.listCategories();
        return ResponseEntity.ok(categoryDtos);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addCategory(@RequestBody CategoryDto categoryDto) {
        Integer result = this.categoryService.create(categoryDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("edit")
    public ResponseEntity<Integer> editCategory(@RequestBody CategoryDto categoryDto) {
        Integer result = this.categoryService.edit(categoryDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        this.categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
