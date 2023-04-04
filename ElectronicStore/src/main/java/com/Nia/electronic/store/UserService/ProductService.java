package com.Nia.electronic.store.UserService;

import com.Nia.electronic.store.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    //get create
    ProductDto create(ProductDto productDto);
    //get update
    ProductDto update(ProductDto productDto,String productId);
  //  get delete
    void delete(String productId);

    //get single
    ProductDto get(String productId);

    //get all
    List<ProductDto> getAll();

    //get all live
    List<ProductDto> getAllLive();

    //search product
    List<ProductDto> searchByTitle(String subTitle);


}
