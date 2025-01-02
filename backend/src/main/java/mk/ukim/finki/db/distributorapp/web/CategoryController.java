package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.CategoryDto;
import mk.ukim.finki.db.distributorapp.model.entities.Category;
import mk.ukim.finki.db.distributorapp.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {
       return ResponseEntity.ok(this.categoryService.listCategories());
    }

    @GetMapping("/all/dto")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesDto() {
        List<CategoryDto> categoryDtos = this.categoryService.listCategoriesDto();
        return ResponseEntity.ok(categoryDtos);
    }
}
