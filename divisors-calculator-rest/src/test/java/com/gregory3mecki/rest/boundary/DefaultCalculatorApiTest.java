package com.gregory3mecki.rest.boundary;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class DefaultCalculatorApiTest {

    private static final String result = "{\"data\":{\"4\":[\"testValue01\",\"testValue02\",\"testValue04\"]}}";

    @Test
    void checkCalculatorReturnsCorrectDivisorsForSimpleCase() {
        given()
                .pathParam("name", "test-mapping")
                .queryParam("number", "4")
                .when().get("/calculator-api/dividers/mappings/{name}")
                .then()
                .statusCode(200)
                .body(is(result));
    }

}
