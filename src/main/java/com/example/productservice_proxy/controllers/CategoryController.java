package com.example.productservice_proxy.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/categories")
//@RequestMapping("/user/categories") to differentiate prefixing with /products
public class CategoryController {

    @GetMapping()
    public String getAllCategories(){
        return "Getting all categories";
    }

    @GetMapping("/{categoryId}")
    public String getProductsInCategory(@PathVariable("categoryId") Long categoryId){
        return "get products in category";
    }
}
