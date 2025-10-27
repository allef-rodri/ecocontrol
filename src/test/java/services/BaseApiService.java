package services;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApiService {

    private static final String BASE_URI = "http://localhost";
    private static final int PORT = 8080;
    private static final String USERNAME = "analista";
    private static final String PASSWORD = "analista123";

    protected RequestSpecification givenJsonAuthenticated() {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .port(PORT)
                .auth().preemptive().basic(USERNAME, PASSWORD)
                .header("Content-Type", "application/json")
                .accept("application/json");
    }
}
