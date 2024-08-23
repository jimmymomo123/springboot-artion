package com.jimmychiu.artion.controller;

import com.jimmychiu.artion.dto.CategoryRequest;
import com.jimmychiu.artion.dto.EventRequest;
import com.jimmychiu.artion.dto.Result;
import com.jimmychiu.artion.entity.Category;
import com.jimmychiu.artion.entity.Event;
import com.jimmychiu.artion.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 获取所有类别
    @GetMapping
    public Result<List<Category>> list() {
        List<Category> categories = categoryService.getAllCategories();
        return Result.success(categories);
    }

    // 根据 ID 获取类别
    @GetMapping("/{categoryId}")
    public Result<Category> getCategory(@PathVariable Integer categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return Result.success(category);
    }

    // 创建新类别
    @PostMapping
    public Result createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryService.createCategory(convertToEntity(categoryRequest));
        return Result.success();
    }

    // 更新类别
    @PutMapping("/{categoryId}")
    public Result updateCategory(@PathVariable Integer categoryId, @Valid @RequestBody CategoryRequest categoryRequest) {
        categoryService.updateCategory(categoryId, convertToEntity(categoryRequest));
        return Result.success();
    }

    // 删除类别
    @DeleteMapping("/{categoryId}")
    public Result<Void> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return Result.success();
    }

    private Category convertToEntity(CategoryRequest dto) {
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);
        return category;
    }

}
