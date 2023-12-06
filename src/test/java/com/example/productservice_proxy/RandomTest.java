package com.example.productservice_proxy;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class RandomTest {
    /*public static void main(String[] args){
        testRandom();
    }
    public static void testRandom(){
        Random random = new Random();
        int a = random.nextInt(10);
        assert(a<5);
    }*/

    @Test
    public void testRandom() {
        Random random = new Random();
        int a = random.nextInt(10);
        assert (a < 5);
    }
}
