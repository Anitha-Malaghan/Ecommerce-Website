package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.ProductService;
import com.example.productservice_proxy.services.ProductServiceInterface;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class productController {

    ProductServiceInterface productService;

    public productController(ProductServiceInterface productService){
        this.productService=productService;
    }

    @GetMapping("")
    public String getAllProducts(){
        return "getting all the products";

    }

    @GetMapping("/{productId}")
    // get the product
    public Product getSingleProduct(@PathVariable("productId") Long productId){
        Product product = this.productService.getSingleProduct(productId);
        return product;
    }

    @PostMapping("/{productId}")
    public String addNewProduct(@RequestBody ProductDto productDto){
        //creating new product
        return "adding new product "+productDto;

    }

    @PatchMapping("/{productId}")
    //The PUT method is used to update an entire resource or create a new resource if it doesn't exist at the specified URI.
    public String updateOrCreateProduct(@PathVariable("productId") Long productId  ){
        return "updating product";

    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "deleting product";

    }


}
