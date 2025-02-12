package mk.ukim.finki.db.distributorapp.category;

import mk.ukim.finki.db.distributorapp.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> listCategories();

    CategoryDto getCategoryById(Integer id);

    List<CategoryDto> searchCategories(String text);

    Integer create(CategoryDto categoryDto);

    Integer edit(CategoryDto categoryDto);

    void deleteById(Integer id);
}
