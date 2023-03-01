package com.alexandrov.tests;

import com.alexandrov.tests.lombok.LombokUserData;
import com.alexandrov.tests.lombok.RequestCreateUser;
import com.alexandrov.tests.lombok.ResponseCreateUser;
import io.qameta.allure.AllureId;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.alexandrov.tests.specs.CreateUserResponseSpec.createUserResponseSpec;
import static com.alexandrov.tests.specs.RegisterSuccessfulResponseSpec.registerSuccessfulResponseSpec;
import static com.alexandrov.tests.specs.RequestSpec.requestSpec;
import static com.alexandrov.tests.specs.ResponseDeleteSpec.responseDeleteSpec;
import static com.alexandrov.tests.specs.ResponseSpec.responseSpec;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasItem;
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

        given()
                .spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .post("api/register")
                .then()
                .spec(registerSuccessfulResponseSpec);
    }

    @Test
    @Tag("api-tests")
    @AllureId("15280")
    @DisplayName("Single user")
    void checkGetSingleUserTest() {
        LombokUserData data = given()
                .spec(requestSpec)
                .when()
                .get("api/users/3")
                .then()
                .spec(responseSpec)
                .extract().as(LombokUserData.class);
        assertEquals(3, data.getUser().getId());
        assertEquals("Emma", data.getUser().getFirstName());
        assertEquals("Wong", data.getUser().getLastName());
        assertEquals("emma.wong@reqres.in", data.getUser().getEmail());
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
                .spec(requestSpec)
                .body(requestBody.toString())
                .when()
                .post("api/users")
                .then()
                .spec(createUserResponseSpec);
    }

    @Test
    @Tag("api-tests")
    @AllureId("15279")
    @DisplayName("Update user")
    void checkPutUpdateTest() {

        RequestCreateUser bodyRequest = new RequestCreateUser();
        bodyRequest.setName("Artem");
        bodyRequest.setJob("QA Engineer");

        ResponseCreateUser response = given()
                .spec(requestSpec)
                .body(bodyRequest)
                .when()
                .put("api/users/2")
                .then()
                .spec(responseSpec)
                .log().all()
                .extract().as(ResponseCreateUser.class);
        assertThat(response.getName().equals("Artem"));
        assertThat(response.getJob().equals("QA Engineer"));
    }

    @Test
    @Tag("api-tests")
    @AllureId("15278")
    @DisplayName("Delete user")
    void checkDeleteTest() {
        given()
                .spec(requestSpec)
                .when()
                .delete("api/users/3")
                .then()
                .spec(responseDeleteSpec);
    }

    @ValueSource(strings = {
            "george.bluth@reqres.in",
            "janet.weaver@reqres.in",
            "emma.wong@reqres.in",
            "eve.holt@reqres.in",
            "charles.morris@reqres.in"
    })
    @ParameterizedTest(name = "Cписок пользователей с id < 6, входит пользователь с email -  {0}")
    public void checkUserEmails(String expectedEmail) {

        given()
                .when()
                .spec(requestSpec)
                .get("api/users")
                .then()
                .spec(responseSpec)
                .body("data.findAll{it.id < 6}.email", hasItem(expectedEmail));
    }
}