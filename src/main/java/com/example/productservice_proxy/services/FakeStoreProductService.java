package com.example.productservice_proxy.services;

import com.example.productservice_proxy.clients.ClientProductDtoInterface;
import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreProductDto;
import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import io.micrometer.common.lang.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;



import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductServiceInterface {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){

        this.restTemplateBuilder = restTemplateBuilder;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
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
    public Product getSingleProduct(Long productId){
        /*MultiValueMap interface from the Spring Framework, LinkedMultiValueMap implementation.
         used to create a map-like data structure that can hold multiple values for a single key.*/

        /*this code snippet is a part of a Java class that utilizes the RestTemplateBuilder to create an instance of RestTemplate,
        which is then used to make a GET request to the specified URL, retrieve the response, and map it to a ProductDto object.
        The method then returns a string representation of this ProductDto object.*/
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> productDto =
                restTemplate.getForEntity(
                        "https://fakestoreapi.com/products/{id}",
                        FakeStoreProductDto.class, productId);
        //need to convert productDto object to product object.
        Product product = getProduct(productDto.getBody());
        return product;
    }


    @Override
    public Product addNewProduct(ClientProductDtoInterface productDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);
        Product product = getProduct((FakeStoreProductDto) productDto);
        return product;

    }


    @Override
    public Product updateProduct(Long productId, Product product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity
                =  requestForEntity(
                        HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );

    FakeStoreProductDto fakeStoreProductDto1 = fakeStoreProductDtoResponseEntity.getBody();
    return getProduct(fakeStoreProductDto);
}
    @Override
    public  String deleteProduct(Long productId){
        return null;
    }
    //transformer method for fakestoreproductdto to product
    private Product getProduct(FakeStoreProductDto productDto) {
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
