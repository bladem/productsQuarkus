package org.pausanchez.services;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.pausanchez.entities.Product;
import org.pausanchez.repositories.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.*;

@ApplicationScoped
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Inject
    private ProductRepository productRepository;

    @Override
    public Uni<Response> add(Product product) {
        log.info("Guardando nuevo producto");

        return Panache.withTransaction(()-> productRepository.persist(product))
                .replaceWith(Response.ok(product).status(CREATED)::build);
    }

    @Override
    public Uni<Response> update(Product product) {
        log.info("Actualizando producto {}", product);

        return Panache
                .withTransaction(() -> productRepository.findById(product.getId())
                        .onItem().ifNotNull().invoke(entity -> {
                            entity.setName(product.getName());
                            entity.setDescription(product.getDescription());
                            entity.setCode(product.getCode());
                        })
                )
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);
    }

    @Override
    public Uni<List<Product>> getProducts() {
        log.info("Obteniendo todos los productos");
        return productRepository.findAll().list();
    }

    @Override
    public Uni<Response> delete(Long id) {
        log.info("Eliminando producto id:{}",id);
        return Panache.withTransaction(() -> productRepository.deleteById(id))
                .map(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build());
    }

    @Override
    public Uni<Product> getById(Long id) {
        log.info("Obteniendo producto by id:{}",id);
        return productRepository.findById(id);
    }

    @Override
    public Uni<Product> addMutation(Product product) {
        return Panache.withTransaction(()-> productRepository.persist(product))
                .replaceWith(product);
    }

    @Override
    public Uni<Boolean> deleteMutation(Long id) {
        return Panache.withTransaction(() -> productRepository.deleteById(id));
    }

    @Override
    public Uni<Product> updateMutation(Product product) {
        return Panache
                .withTransaction(() -> productRepository.findById(product.getId())
                        .onItem().ifNotNull().invoke(entity -> {
                            entity.setName(product.getName());
                            entity.setDescription(product.getDescription());
                            entity.setCode(product.getCode());
                        })
                );
    }
}
