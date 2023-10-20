package com.example.productservice_proxy.inheritanceexamples.tableperclass.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_ta")
public class TA extends User{
    private double rating;

}
