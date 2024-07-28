package com.bangvan.apiblogapp.service.impl;

import com.bangvan.apiblogapp.dto.request.CategoryRequest;
import com.bangvan.apiblogapp.dto.response.CategoryResponse;
import com.bangvan.apiblogapp.entity.Category;
import com.bangvan.apiblogapp.exception.ResourceNotFoundException;
import com.bangvan.apiblogapp.repository.CategoryRepository;
import com.bangvan.apiblogapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest){
        Category category = modelMapper.map(categoryRequest,Category.class);
        return modelMapper.map(categoryRepository.save(category),CategoryResponse.class);
    }

    @Override
    public CategoryResponse getCategoryById(String id){
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
        return modelMapper.map(category,CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAllCategory(){
        return categoryRepository.findAll().stream().map(result->modelMapper.map(result,CategoryResponse.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryResponse updateCategory(String id, CategoryRequest categoryRequest){
        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "id", id));
        modelMapper.map(categoryRequest,category);
        return modelMapper.map(categoryRepository.save(category),CategoryResponse.class);
    }

    @Override
    public String deleteCategory(String id){
        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
        return "Category with id: " + id + "was deleted successfully";
    }

}
