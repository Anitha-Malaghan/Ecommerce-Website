package com.example.productservice_proxy.repositories;

import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;
    @Test
    @Transactional
    void saveProductAndCategory() {

        Categories categories = new Categories();
        categories.setName("Electronics");
        categories.setDescription("Electronics");
        categoryRepo.save(categories);

        Product product = new Product();
        product.setTitle("Laptop");
        product.setDescription(("laptop"));
        product.setCategory(categories);
        productRepo.save(product);

        Categories categories1 = categoryRepo.findById(categories.getId()).get();
        List<Product> productList = categories1.getProductList();
        System.out.println("Debug");

    }
    @Test
    @Transactional
    void saveProductAndCategory1() {
        Product product = new Product();
        product.setTitle("Laptop");
        product.setDescription(("laptop"));
        productRepo.save(product);

        Categories categories = new Categories();
        categories.setName("Electronics");
        categories.setDescription("Electronics");
        categories.setProductList(List.of(product));
        categoryRepo.save(categories);



        Categories categories1 = categoryRepo.findById(categories.getId()).get();
        List<Product> productList = categories1.getProductList();
        System.out.println("Debug");

    }
    @Test
    //@Transactional
    void saveProductAndCategory2() {

        Categories categories = new Categories();
        categories.setName("Fashion");
        categories.setDescription("Fashion");
        //categories = categoryRepo.save(categories);


        Product product = new Product();
        product.setTitle("Tshirt");
        product.setDescription("Tshirt");
        product.setCategory(categories);
        productRepo.save(product);

        //Categories categories1 = categoryRepo.findById(categories.getId()).get();
        //List<Product> productList = categories1.getProductList();
        System.out.println("Debug");

    }
    @Test
    // if you are using @transactional at the test it is not going to save the data
    @Transactional
    // you can save by making @Rollback(value = false)
    @Rollback(value = false)
    void saveProductAndCategory3(){
        Categories category = categoryRepo.findById(2L);
        List<Product> productList = category.getProductList();
        for(Product product: productList){
            System.out.println(product.getPrice());
        }
        /*Product product = new Product();
        product.setPrice(1012);
        product.setImageUrl("hiii");
        product.setCategory(category);
        Product savedProduct = productRepo.save(product);

       product = new Product();
       product.setPrice(103);
       product.setImageUrl("hiii");
       product.setCategory(category);
       productRepo.save(product);*/


    }
    @Test
    // if you are using @transactional at the test it is not going to save the data
    @Transactional
    // you can save by making @Rollback(value = false)
    //@Rollback(value = false)
    void saveProductAndCategory4(){


       // Product product = productRepo.findProductById(202L);

        //Product product = productRepo.findByPriceBetween(1000, 1100);
        //List<Product> productList = productRepo.findAll();

        //String s = productRepo.findTitleById(202l);
        //List<Product> productList = productRepo.findByIdIsNotNullOrderByPrice();
        List<Product> productList = productRepo.findByPriceIsNotNullOrderByPrice();
        System.out.println("Debug");
        /*Product product = new Product();
        product.setPrice(1012);
        product.setImageUrl("hiii");
        product.setCategory(category);
        Product savedProduct = productRepo.save(product);

       product = new Product();
       product.setPrice(103);
       product.setImageUrl("hiii");
       product.setCategory(category);
       productRepo.save(product);*/


    }

}