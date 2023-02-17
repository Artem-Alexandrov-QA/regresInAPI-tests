package com.alexandrov.tests;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresInAPITests {

    @Test
    @DisplayName("Login - Successful")
    void checkRegisterSuccessful() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "pistol");

        JSONObject expectedBody = new JSONObject();
        expectedBody.put("id", 4);
        expectedBody.put("token", "QpwL5tke4Pnpja7X4");

        String actualBody = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract()
                .response().getBody().asString();
        assertEquals(expectedBody.toString(), actualBody);
    }

    @Test
    @DisplayName("Single user")
    void checkGetSingleUserTest() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("https://reqres.in/api/users/3")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .assertThat()
                .body("data.id", is(3))
                .body("data.email", is("emma.wong@reqres.in"))
                .body("data.first_name", is("Emma"))
                .body("data.last_name", is("Wong"))
                .body("support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    @DisplayName("Create User")
    void checkPostCreateTest() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Artem");
        requestBody.put("job", "SimbirSoft");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Artem"))
                .body("job", is("SimbirSoft"));
    }

    @Test
    @DisplayName("Update user")
    void checkPutUpdateTest() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Artem");
        requestBody.put("job", "QA Engineer");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("Artem"))
                .body("job", is("QA Engineer"));
    }

    @Test
    @DisplayName("Delete user")
    void checkDeleteTest() {

        given()
                .log().uri()
                .log().body()
                .when()
                .delete("https://reqres.in/api/users/3")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}