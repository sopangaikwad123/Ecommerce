package com.Nia.electronic.store.impl;

import com.Nia.electronic.store.UserService.ProductService;
import com.Nia.electronic.store.dtos.PageableResponse;
import com.Nia.electronic.store.dtos.ProductDto;
import com.Nia.electronic.store.entites.Product;
import com.Nia.electronic.store.exceptions.ResourceNotFoundException;
import com.Nia.electronic.store.helper.Helper;
import com.Nia.electronic.store.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class ProductServiceImpl implements ProductService {
@Autowired
private ProductRepository productRepository;
@Autowired
private ModelMapper mapper;
    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = mapper.map(productDto,Product.class);
        Product saveProduct1 = productRepository.save(product);
        return mapper.map(saveProduct1,ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
//fetch the product of given id

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found of given id !!"));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setProductId(productDto.getProductId());
        product.setPrice(productDto.getPrice());
        product.setDiscount(productDto.getDiscount());
        product.setQuantity(product.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());

        Product updatedProduct = productRepository.save(product);
        return mapper.map(updatedProduct,ProductDto.class);
    }

    @Override
    public void delete(String productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found of given id !!"));
        productRepository.delete(product);

    }

    @Override
    public ProductDto get(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found of given id !!"));

        return mapper.map(product,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).descending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<Product> page = productRepository.findAll(pageable);
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).descending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<Product> page = productRepository.findAll(pageable);


        return  Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir) {
        Sort sort =(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findByTitleContaining(subTitle, pageable);
        return  Helper.getPageableResponse(page,ProductDto.class);
    }
}
