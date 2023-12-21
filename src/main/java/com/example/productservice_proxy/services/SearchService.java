package com.example.productservice_proxy.services;

import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.repositories.ProductRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class SearchService {
    private ProductRepo productRepo;

    public SearchService(ProductRepo productRepo){

        this.productRepo = productRepo;
    }

    public List<Product> searchProducts(String query, int pageNumber, int sizeOfPage){
        //who is passing the pageable to the repository: service
            //return productRepo.findByTitle(query);
            return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber, sizeOfPage));
    }

}
