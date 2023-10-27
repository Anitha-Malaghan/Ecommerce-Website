package com.example.productservice_proxy.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{

    private String title;
    private double price;
    private String description;
    // by default it is eager loading
    @ManyToOne(cascade = CascadeType.ALL)
    private Categories category;
    private String imageUrl;
}
