package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService implements ProductServiceInterface {
    private RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder){

        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getSingleProduct(Long productId){
        /*this code snippet is a part of a Java class that utilizes the RestTemplateBuilder to create an instance of RestTemplate,
        which is then used to make a GET request to the specified URL, retrieve the response, and map it to a ProductDto object.
        The method then returns a string representation of this ProductDto object.
         */
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto  productDto =
                restTemplate.getForEntity(
                        "https://fakestoreapi.com/products/{id}",
                        ProductDto.class, productId).getBody();

        Product product = getProduct(productDto);
        return product;
    }



    @Override
    public String getAllProducts(){
        return null;
    }


    @Override
    public String addNewProduct(ProductDto productDto){
        return null;
    }
    @Override
    public String updateProduct(Long productId){
        return null;
    }

    @Override
    public  String deleteProduct(Long productId){
        return null;
    }
    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        return product;
    }
}
