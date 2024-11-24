package com.example.fashion_shop_management.service.category;

import com.example.fashion_shop_management.dto.category.CategoryDto;
import com.example.fashion_shop_management.entity.Category;
import com.example.fashion_shop_management.exception.NotFoundException;
import com.example.fashion_shop_management.mapper.CategoryMapper;
import com.example.fashion_shop_management.repository.CategoryRepository;
import com.example.fashion_shop_management.service.category.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryDto)));
    }

    @Override
    public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found"));
        category.setName(categoryDto.getName());
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Category not found");
        }
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        return categoryMapper.toDto(categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found")));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    }
}
