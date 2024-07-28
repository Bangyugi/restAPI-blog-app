package com.bangvan.apiblogapp.service;

import com.bangvan.apiblogapp.dto.request.CategoryRequest;
import com.bangvan.apiblogapp.dto.response.CategoryResponse;
import com.bangvan.apiblogapp.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest categoryRequest);

    CategoryResponse getCategoryById(String id);

    List<CategoryResponse> getAllCategory();

    CategoryResponse updateCategory(String id, CategoryRequest categoryRequest);

    String deleteCategory(String id);
}
