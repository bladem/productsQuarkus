package org.pausanchez;

import org.pausanchez.entities.Product;
import org.pausanchez.repositories.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductApi {

    @Inject
    private ProductRepository productRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    public Response add(Product product){
        productRepository.createProduct(product);

        return Response.ok().build();
    }

    @GET
    public List<Product> list(){
        return productRepository.listProduct();
    }

    @DELETE
    public Response delete(Product product){
        productRepository.deleteProduct(product);
        return Response.ok().build();
    }
}