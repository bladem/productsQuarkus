package org.pausanchez;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.smallrye.mutiny.Uni;
import org.pausanchez.entities.Product;
import org.pausanchez.services.ProductService;

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
    private ProductService productService;

    @POST
    public Response add(Product product){
        productService.add(product);
        return Response.ok().build();
    }

    @PUT
    public Response update(Product product){
        if (product == null || product.getCode() == null) {
            throw new WebApplicationException("Product code was not set on request.", HttpResponseStatus.UNPROCESSABLE_ENTITY.code());
        }
        productService.update(product);
        return Response.ok().build();
    }

    @GET
    public Uni<List<Product>> getProducts(){
        return productService.getProducts();
    }

    @DELETE
    @Path("/product/{id}")
    public Response delete(@PathParam("id") Long id){
        productService.delete(id);
        return Response.ok().build();
    }

    @GET
    @Path("/product/{id}")
    public Uni<Product> getById(@PathParam("id") Long id){
        return productService.getById(id);
    }
}