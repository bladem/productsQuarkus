package org.pausanchez;

import org.pausanchez.entities.Product;
import org.pausanchez.repositories.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductApi {

    @Inject
    private ProductRepository productRepository;

    @POST
    public Response add(Product product){
        productRepository.createProduct(product);

        return Response.ok().build();
    }

    @PUT
    public Response update(Product product){
        productRepository.updateProduct(product);
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