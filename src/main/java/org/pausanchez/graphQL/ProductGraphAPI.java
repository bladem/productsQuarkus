package org.pausanchez.graphQL;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.pausanchez.entities.Product;
import org.pausanchez.services.ProductService;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class ProductGraphAPI {

    @Inject
    private ProductService productService;

    @Query("allProducts")
    public Uni<List<Product>> getAllProducts(){
        return productService.getProducts();
    }

    @Query("productById")
    public Uni<Product> getProductById(Long id){
        return productService.getById(id);
    }

    @Mutation
    public Uni<Product> addProduct(Product product){
        return productService.addMutation(product);
    }

    @Mutation
    public Uni<Boolean> deleteProduct(Long id){
        return productService.deleteMutation(id);
    }

    @Mutation
    public Uni<Product> updateProduct(Product product){
        return productService.updateMutation(product);
    }
}
