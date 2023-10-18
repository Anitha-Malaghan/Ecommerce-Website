package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.clients.ClientProductDtoInterface;
import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.ProductServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductServiceInterface productService;

//constructor
    public ProductController(ProductServiceInterface productService){

        this.productService=productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);

    }

    @GetMapping("/{productId}")
    // get the product
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId){
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept","application/json");
            headers.add("Content-type","application/json");
            headers.add("auth-token", "heyaccess");
            Product product = this.productService.getSingleProduct(productId);
            if(productId<1){
                throw new IllegalArgumentException("Something went wrong");
            }
            /*return ResponseEntity.ok(product); */
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product,headers, HttpStatus.OK);
        return responseEntity;
        }
        catch(Exception e){
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500 error code
        }

    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ClientProductDtoInterface productDto){
        Product product = this.productService.addNewProduct(productDto);
        //why conversion here from product to responseEntity?
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;

    }

    @PatchMapping("/{productId}")
    //The PUT method is used to update an entire resource or create a new resource if it doesn't exist at the specified URI.
    public Product patchProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto  ){
       Product product = new Product();
       product.setId(productDto.getId());
       product.setCategory(new Categories());
       product.getCategory().setName(productDto.getCategory());
       product.setTitle(productDto.getTitle());
       product.setPrice(productDto.getPrice());
       product.setDescription(productDto.getDescription());
       return this.productService.updateProduct(productId, product);

    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "deleting product";

    }


}
