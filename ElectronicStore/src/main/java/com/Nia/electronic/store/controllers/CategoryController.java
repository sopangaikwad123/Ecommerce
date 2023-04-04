package com.Nia.electronic.store.controllers;

import com.Nia.electronic.store.UserService.CategoryService;
import com.Nia.electronic.store.dtos.ApiResponseMessage;
import com.Nia.electronic.store.dtos.CategoryDto;
import com.Nia.electronic.store.dtos.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        //call service to save object
        CategoryDto categoryDto1 = categoryService.create(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);

    }

    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId, @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<ApiResponseMessage> deleteCategory (@PathVariable String categoryId){
        categoryService.delete(categoryId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Category is deleted successfully !!").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(responseMessage,HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAll(
            @RequestParam(value ="pageNumber",defaultValue ="0",required = false) int pageNumber,
            @RequestParam(value ="pagesize",defaultValue ="0",required = false) int pagesize,
            @RequestParam(value ="sortBy",defaultValue ="title",required = false) String sortBy,
            @RequestParam(value ="sortDir",defaultValue ="asc",required = false) String sortDir) {
        PageableResponse<CategoryDto> pageableResponse = categoryService.getAll(pageNumber, pagesize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);

    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingle(@PathVariable String categoryId){
        CategoryDto categoryDto = categoryService.get(categoryId);
        return ResponseEntity.ok(categoryDto);

    }




}