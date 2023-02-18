package org.pausanchez.repositories;

import org.pausanchez.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductRepository {

    @Inject
    private EntityManager entityManager;

    @Transactional
    public void createProduct(Product product){
        entityManager.persist(product);
    }

    @Transactional
    public void deleteProduct(Product product){
        entityManager.remove(product);
    }

    @Transactional
    public List<Product> listProduct(){
        return entityManager.createQuery("select p from Product p").getResultList();
    }

    @Transactional
    public void updateProduct(Product product){
        entityManager.merge(product);
    }
}
