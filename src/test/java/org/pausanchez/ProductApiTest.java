package org.pausanchez;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.pausanchez.entities.Product;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ProductApiTest {

    @Test
    public void testAddProduct(){
        Product product = new Product();
        product.setDescription("hola");
        product.setName("pau");

        given().contentType(ContentType.JSON)
                .body(product)
                .when().post("/product")
                .then()
                .statusCode(200);
    }

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/product")
          .then()
             .statusCode(200);
    }

}