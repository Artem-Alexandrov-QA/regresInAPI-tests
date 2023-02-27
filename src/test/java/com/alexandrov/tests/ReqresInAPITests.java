package com.alexandrov.tests;

import com.alexandrov.tests.filters.AllureRestAssuredFilter;;
import io.qameta.allure.AllureId;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresInAPITests {

    @Test
    @Tag("api-tests")
    @AllureId("15281")
    @DisplayName("Login - Successful")
    void checkRegisterSuccessful() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "pistol");

        JSONObject expectedBody = new JSONObject();
        expectedBody.put("id", 4);
        expectedBody.put("token", "QpwL5tke4Pnpja7X4");

        String actualBody =
                given()
                        .filter(AllureRestAssuredFilter.withCustomTemplates())
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
    @Tag("api-tests")
    @AllureId("15280")
    @DisplayName("Single user")
    void checkGetSingleUserTest() {
        given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
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
    @Tag("api-tests")
    @AllureId("15282")
    @DisplayName("Create User")
    void checkPostCreateTest() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Artem");
        requestBody.put("job", "SimbirSoft");

        given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
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
    @Tag("api-tests")
    @AllureId("15279")
    @DisplayName("Update user")
    void checkPutUpdateTest() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Artem");
        requestBody.put("job", "QA Engineer");

        given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
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
    @Tag("api-tests")
    @AllureId("15278")
    @DisplayName("Delete user")
    void checkDeleteTest() {

        given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
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