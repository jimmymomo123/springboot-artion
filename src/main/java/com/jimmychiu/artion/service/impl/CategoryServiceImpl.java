package com.jimmychiu.artion.service.impl;

import com.jimmychiu.artion.entity.Category;
import com.jimmychiu.artion.repository.CategoryRepository;
import com.jimmychiu.artion.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryRepo.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + categoryId));
    }

    @Override
    public void createCategory(Category category) {
        category.setCreatedTime(LocalDateTime.now());
        category.setUpdatedTime(LocalDateTime.now());
        categoryRepo.save(category);
    }

    @Override
    public void updateCategory(Integer categoryId, Category categoryDeatils) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + categoryId));
        category.setUpdatedTime(LocalDateTime.now());
        if (categoryDeatils.getName() != null){
            category.setName(categoryDeatils.getName());
        }
        if (categoryDeatils.getCreateAdminName() != null){
            category.setCreateAdminName(categoryDeatils.getCreateAdminName());
        }
        categoryRepo.save(category);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + categoryId));
        categoryRepo.delete(category);
    }
}
