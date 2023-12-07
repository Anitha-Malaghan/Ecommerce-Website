package com.example.productservice_proxy.clients.fakestore.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FakeStoreClientTest {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    void testNonExiststringProductReturnNull(){

    }
}