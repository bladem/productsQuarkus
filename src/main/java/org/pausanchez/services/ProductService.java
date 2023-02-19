package org.pausanchez.services;

import org.pausanchez.entities.Product;

import java.util.List;

public interface ProductService {

    void add(Product product);

    void update(Product product);

    List<Product> getProducts();

    void delete(Long id);

    Product getById(Long id);
}
