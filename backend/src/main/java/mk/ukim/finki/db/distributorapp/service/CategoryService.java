package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.CategoryDto;
import mk.ukim.finki.db.distributorapp.model.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> listCategories();

    List<CategoryDto> listCategoriesDto();

    Optional<Category> getCategoryById(Long id);

    Optional<Category> createCategory(String name);

    Optional<Category> updateCategory(Long id, String name);

    void delete(Long id);

    List<Category> searchCategories(String text);
}
