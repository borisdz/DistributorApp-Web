package mk.ukim.finki.db.distributorapp.category;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.category.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> listCategories() {
        return this.categoryRepository.listAll();
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

}