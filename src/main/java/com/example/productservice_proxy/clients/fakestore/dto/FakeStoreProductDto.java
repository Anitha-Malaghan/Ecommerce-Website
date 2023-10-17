package com.example.productservice_proxy.clients.fakestore.dto;

import com.example.productservice_proxy.clients.ClientProductDtoInterface;
import com.example.productservice_proxy.dtos.RatingDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto implements ClientProductDtoInterface {

//if won't write @ToString it will give address of the productDto

        private Long id;
        private String title;
        private double price;
        private String description;
        private String image;
        private String category;
        private RatingDto rating;

}
