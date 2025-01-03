package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.dto.CategoryDto;
import mk.ukim.finki.db.distributorapp.model.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDto> listCategories();

    Optional<Category> getCategoryById(Long id);

    Integer create(CategoryDto categoryDto);

    Integer update(CategoryDto categoryDto);

    void delete(Long id);

    List<CategoryDto> searchCategories(String text);
}
