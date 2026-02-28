package RestAssured;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static java.lang.Math.log;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class TestGetRest {

    @Test
    public void testGet(){
        baseURI = "https://fakestoreapi.com";
        Response response =
        given().
                get("/products").
                then()
                .statusCode(200)
                .log().ifValidationFails()
                .extract().response();

        long responseTime = response.getTime();

        System.out.println("-------------------------------");
        System.out.println("Execution Successful!");
        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Time: " + responseTime + "ms");
        System.out.println("Response Body: " + response.path("title"));

    }

    @Test
    public void testPost(){
        baseURI = "https://fakestoreapi.com";

        Product postPayLoad = new Product(
                "Jacket",
                250000,
                "Outwear",
                "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
                "women's clothing"

        );

        Response response =
                given()
                        .log().all() // Logs request details
                        .contentType(ContentType.JSON)
                        .body(postPayLoad)
                        .when()
                        .post("/products")
                        .then()
                        .log().ifValidationFails() // Logs response only if an assertion fails
                        .statusCode(201)
                        .body("title", equalTo("Jacket"))
                        .body("price", is(250000))
                        .extract().response();

        // 3. Extract and Display Timing Variable
        long responseTime = response.getTime();

        System.out.println("-------------------------------");
        System.out.println("Execution Successful!");
        System.out.println("Response Time: " + responseTime + "ms");
        System.out.println("Title created: " + response.path("title"));




    }

    @Test
    public void testPut(){
        baseURI = "https://fakestoreapi.com";

        UpdateProduct putPayLoad = new UpdateProduct(
                21,
                "Blouse",
                230000,
                "Silk blouse",
                "Outwear",
                "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg"

        );

        Response response =
                given()
                        .log().all() // Logs request details
                        .contentType(ContentType.JSON)
                        .body(putPayLoad)
                        .when()
                        .put("/products/21")
                        .then()
                        .log().ifValidationFails() // Logs response only if an assertion fails
                        .statusCode(200)
                        .body("title", equalTo("Blouse"))
                        .body("price", is(230000))
                        .extract().response();

        long responseTime = response.getTime();

        System.out.println("-------------------------------");
        System.out.println("Execution Successful!");
        System.out.println("Response Time: " + responseTime + "ms");
        System.out.println("Title created: " + response.path("title"));
    }
    @Test
    public void testDelete(){
        baseURI = "https://fakestoreapi.com";
        Response response =
                given().
                        log().all() // Logs request details
                        .delete("/products/21").
                        then()
                        .statusCode(200)
                        .log().ifValidationFails()
                        .extract().response();

        long responseTime = response.getTime();

        System.out.println("-------------------------------");
        System.out.println("Execution Successful!");
        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Time: " + responseTime + "ms");



    }
}
