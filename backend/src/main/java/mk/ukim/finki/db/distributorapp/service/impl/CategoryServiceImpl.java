package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.CategoryDto;
import mk.ukim.finki.db.distributorapp.model.entities.Category;
import mk.ukim.finki.db.distributorapp.repository.CategoryRepository;
import mk.ukim.finki.db.distributorapp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    private List<CategoryDto> buildDto(List<Category> categories) {
        List<CategoryDto> dtos = new ArrayList<>();
        for (Category category : categories) {
            CategoryDto dto = new CategoryDto(
                    category.getCategoryId(),
                    category.getCategoryName()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<CategoryDto> listCategories() {
        List<Category> categories = this.categoryRepository.listAll();
        return buildDto(categories);
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category ctg = this.categoryRepository.findById(id).get();
        return new CategoryDto(
                ctg.getCategoryId(),
                ctg.getCategoryName()
        );
    }

    @Override
    public Integer create(CategoryDto categoryDto) {
        return this.categoryRepository.create(categoryDto.getName());
    }

    @Override
    public Integer edit(CategoryDto categoryDto) {
        return this.categoryRepository.edit(categoryDto.getId(), categoryDto.getName());
    }

    @Override
    public void deleteById(Integer id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> searchCategories(String text) {
        List<Category> categories = this.categoryRepository.findAllByName("'"+text+"'");
        return buildDto(categories);
    }
}