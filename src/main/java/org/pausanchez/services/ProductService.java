package org.pausanchez.services;

import io.smallrye.mutiny.Uni;
import org.pausanchez.entities.Product;

import javax.ws.rs.core.Response;
import java.util.List;

public interface ProductService {

    Uni<Response> add(Product product);

    Uni<Response> update(Product product);

    Uni<List<Product>> getProducts();

    Uni<Response> delete(Long id);

    Uni<Product> getById(Long id);
}
