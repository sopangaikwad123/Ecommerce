package com.Nia.electronic.store.impl;

import com.Nia.electronic.store.UserService.CategoryService;
import com.Nia.electronic.store.dtos.CategoryDto;
import com.Nia.electronic.store.dtos.PageableResponse;
import com.Nia.electronic.store.entites.Category;
import com.Nia.electronic.store.exceptions.ResourceNotFoundException;
import com.Nia.electronic.store.helper.Helper;
import com.Nia.electronic.store.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class CategoryServiceImpl  implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;


    @Override
    public CategoryDto create(CategoryDto categoryDto) {


        String categoryId= UUID.randomUUID().toString();
        categoryDto.setCategoryId(categoryId);

        Category category = mapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return mapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {

         // get category of given id
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found Exception !!"));

        //update category details
        category.setTitle(categoryDto.getTitle());
        category.setDescrption(categoryDto.getDescrption());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepository.save(category);
        return mapper.map(updatedCategory, CategoryDto.class);
    }
//get category of given id
    @Override
    public void delete(String categoryId) {
        Category category =categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found Exception"));
        this.categoryRepository.delete(category);

    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize,String sortBy,String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ?(Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
         Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> page= categoryRepository.findAll(pageable);

        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);

        return pageableResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {
       Category category= categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found Exception"));

        return mapper.map(category,CategoryDto.class);
    }
}
