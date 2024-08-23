package com.jimmychiu.artion.service;

import com.jimmychiu.artion.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Integer categoryId);

    void createCategory(Category category);

    void updateCategory(Integer categoryId, Category categoryDeatils);

    void deleteCategory(Integer categoryId);
}
