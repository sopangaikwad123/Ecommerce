package com.Nia.electronic.store.repositories;

import com.Nia.electronic.store.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, String> {

    List<Product> findByTitleContaining(String subTitle);

    List<Product> findByLiveTrue();


}
