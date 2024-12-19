package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.Category;
import mk.ukim.finki.db.distributorapp.repository.CategoryRepository;
import mk.ukim.finki.db.distributorapp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private boolean categoryInvalid(String name) {
        return name == null || name.isEmpty();
    }

    @Override
    public List<Category> listCategories() {
        return this.categoryRepository.listAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> createCategory(String name, String description) {
        if (categoryInvalid(name)) {
            throw new IllegalArgumentException();
        }
        return this.categoryRepository.create(name, description);
    }

    @Override
    public Optional<Category> updateCategory(Long id, String name, String description) {
        return this.categoryRepository.edit(id,name,description);
    }

    @Override
    public void delete(Long id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> searchCategories(String text) {
        return List.of();
    }
}