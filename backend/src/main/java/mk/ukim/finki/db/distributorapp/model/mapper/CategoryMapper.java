package mk.ukim.finki.db.distributorapp.model.mapper;

import mk.ukim.finki.db.distributorapp.model.dto.CategoryDto;
import mk.ukim.finki.db.distributorapp.model.entities.Category;

public class CategoryMapper {
    private static CategoryDto toCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getCategoryId());
        categoryDto.setName(category.getCategoryName());
        return categoryDto;
    }
}
