package com.example.productservice_proxy.repositories;

import com.example.productservice_proxy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save(Product product);
    Product findProductById(Long Id);
    Product findByPriceBetween(double greater_than, double less_than);
    //Product findByProductName(String productName);

   // String findTitleById(Long id);
    List<Product> findAll();

    //List<Product> findByIdIsNotNullOrderByPrice();
    List<Product>findByPriceIsNotNullOrderByPrice();

    // I can get tall the value which are public.this works for boolean only.
    List<Product> findAllByIsPublicTrue();


}
