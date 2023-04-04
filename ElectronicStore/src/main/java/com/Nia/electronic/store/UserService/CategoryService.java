package com.Nia.electronic.store.UserService;

import com.Nia.electronic.store.dtos.CategoryDto;
import com.Nia.electronic.store.dtos.PageableResponse;

public interface CategoryService {

    //create
    CategoryDto create (CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto, String categoryId);

    //delete
    void delete( String categoryId);

    //get All
    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize,String sortBy,String sortDir);
    //get single category detail
    CategoryDto get(String categoryId);



}
