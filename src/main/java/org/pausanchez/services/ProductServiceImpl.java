package org.pausanchez.services;

import lombok.extern.slf4j.Slf4j;
import org.pausanchez.entities.Product;
import org.pausanchez.repositories.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Inject
    private ProductRepository productRepository;

    @Override
    public void add(Product product) {
        log.info("Guardando nuevo producto");
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        log.info("Actualizando producto {}", product);
        Product product1 = productRepository.findById(product.getId()).orElseThrow();
        product1.setCode(product.getCode());
        product1.setDescription(product.getDescription());
        product1.setName(product.getName());

        productRepository.save(product1);
    }

    @Override
    public List<Product> getProducts() {
        log.info("Obteniendo todos los productos");
        return productRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        log.info("Eliminando producto id:{}",id);
        productRepository.delete(productRepository.findById(id).orElseThrow());
    }

    @Override
    public Product getById(Long id) {
        log.info("Obteniendo producto by id:{}",id);
        return productRepository.findById(id).orElseThrow();
    }
}
