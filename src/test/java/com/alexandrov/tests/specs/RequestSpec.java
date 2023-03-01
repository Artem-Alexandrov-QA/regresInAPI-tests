package com.alexandrov.tests.specs;

import com.alexandrov.tests.filters.AllureRestAssuredFilter;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RequestSpec {

public static RequestSpecification requestSpec = with()
        .filter(AllureRestAssuredFilter.withCustomTemplates())
        .baseUri("https://reqres.in")
        .log().body()
        .contentType(JSON);
}
