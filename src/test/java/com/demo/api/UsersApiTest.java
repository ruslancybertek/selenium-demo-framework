package com.demo.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

/**
 * API tests with REST Assured against the public JSONPlaceholder demo API.
 */
public class UsersApiTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "GET /users returns a list of users")
    public void getUsersReturnsList() {
        given()
            .when()
                .get("/users")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThan(0))
                .body("[0].email", notNullValue());
    }

    @Test(description = "POST /users creates a user")
    public void createUserReturns201() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"name\": \"QA Demo\", \"email\": \"qa@example.com\"}")
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .body("name", equalTo("QA Demo"))
                .body("id", notNullValue());
    }

    @Test(description = "GET /users/1 returns a single user")
    public void getSingleUser() {
        given()
            .when()
                .get("/users/1")
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("username", notNullValue());
    }
}
