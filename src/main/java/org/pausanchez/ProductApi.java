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
        productRepository.save(product);

        return Response.ok().build();
    }

    @PUT
    public Response update(Product product){
        Product product1 = productRepository.findById(product.getId()).get();
        product1.setCode(product.getCode());
        product1.setDescription(product.getDescription());
        product1.setName(product.getName());

        productRepository.save(product1);
        return Response.ok().build();
    }

    @GET
    public List<Product> list(){
        return productRepository.findAll();
    }

    @DELETE
    @Path("/product/{id}")
    public Response delete(@PathParam("id") Long id){
        productRepository.delete(productRepository.findById(id).get());
        return Response.ok().build();
    }

    @GET
    @Path("/product/{id}")
    public Product getById(@PathParam("id") Long id){
        return productRepository.findById(id).get();
    }
}