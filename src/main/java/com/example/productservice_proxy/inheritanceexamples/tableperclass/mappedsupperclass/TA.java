package com.example.productservice_proxy.inheritanceexamples.tableperclass.mappedsupperclass;

import jakarta.persistence.Entity;

@Entity(name = "mps_ta")
public class TA extends User {
    private double rating;

}
