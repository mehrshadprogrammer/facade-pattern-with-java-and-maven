package org.example.service;

import org.example.entity.Product;

public class ProductService {
    public Product create(String name, double price, int count){
        return new Product(name, price, count);
    }
}
