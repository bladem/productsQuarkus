package org.pausanchez.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import org.pausanchez.entities.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepositoryBase<Product, Long> {
}
