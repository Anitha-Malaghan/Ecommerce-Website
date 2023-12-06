package com.example.productservice_proxy.inheritanceexamples.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_mentor")
public class Mentor extends User{
    private int gradYear;
}
