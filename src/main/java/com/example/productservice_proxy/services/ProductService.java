package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {
    private RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder){

        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getSingleProduct(Long productId){
        /*MultiValueMap interface from the Spring Framework, LinkedMultiValueMap implementation.
         used to create a map-like data structure that can hold multiple values for a single key.*/

        /*this code snippet is a part of a Java class that utilizes the RestTemplateBuilder to create an instance of RestTemplate,
        which is then used to make a GET request to the specified URL, retrieve the response, and map it to a ProductDto object.
        The method then returns a string representation of this ProductDto object.*/
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> productDto =
                restTemplate.getForEntity(
                        "https://fakestoreapi.com/products/{id}",
                        ProductDto.class, productId);
        //need to convert productDto object to product object.
        Product product = getProduct(productDto.getBody());
        return product;
    }

    @Override
    public List<Product> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> productDtos = restTemplate
                .getForEntity("https://fakestoreapi.com/products", ProductDto[].class);

        List<Product> answer = new ArrayList<>();
        for(ProductDto productDto: productDtos.getBody()){
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            Categories category = new Categories();
            category.setName(productDto.getCategory());
            product.setCategory(category);
            product.setImageUrl(productDto.getImage());
            answer.add(product);
        }

        return answer;
    }


    @Override
    public Product addNewProduct(ProductDto productDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);
        Product product = getProduct(productDto);
        return product;

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
