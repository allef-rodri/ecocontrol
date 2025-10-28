package services;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApiService {

    private static final String BASE_URI = "http://localhost";
    private static final int PORT = 8080;
    private static final String USERNAME = "analista";
    private static final String PASSWORD = "analista123";

    protected RequestSpecification givenJsonAuthenticated() {
        return givenJsonWithCredentials(USERNAME, PASSWORD);
    }

    protected RequestSpecification givenJsonWithCredentials(String username, String password) {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .port(PORT)
                .auth().preemptive().basic(username, password)
                .header("Content-Type", "application/json")
                .accept("application/json");
    }

    protected RequestSpecification givenJsonWithoutAuth() {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .port(PORT)
                .header("Content-Type", "application/json")
                .accept("application/json");
    }
}
