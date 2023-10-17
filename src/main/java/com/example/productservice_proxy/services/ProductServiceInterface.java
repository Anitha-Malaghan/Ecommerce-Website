package com.example.productservice_proxy.services;

import com.example.productservice_proxy.clients.ClientProductDtoInterface;
import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Product;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);

    Product addNewProduct(ClientProductDtoInterface productDto);

    String updateProduct(Long productId);

    String deleteProduct(Long productId);
}
