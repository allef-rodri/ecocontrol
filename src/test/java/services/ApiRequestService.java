package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;

public class ApiRequestService extends BaseApiService {

    private final Gson gson = new GsonBuilder().create();
    private Response lastResponse;

    public void sendGet(String endpoint) {
        lastResponse = givenJsonAuthenticated()
                .when()
                .get(endpoint);
    }

    public void sendPost(String endpoint, Object requestBody) {
        lastResponse = givenJsonAuthenticated()
                .body(toJson(requestBody))
                .when()
                .post(endpoint);
    }

    public void sendPostWithoutAuth(String endpoint, Object requestBody) {
        lastResponse = givenJsonWithoutAuth()
                .body(toJson(requestBody))
                .when()
                .post(endpoint);
    }

    public void sendPostWithCredentials(String endpoint, Object requestBody, String username, String password) {
        lastResponse = givenJsonWithCredentials(username, password)
                .body(toJson(requestBody))
                .when()
                .post(endpoint);
    }

    public void sendPut(String endpoint, Object requestBody) {
        lastResponse = givenJsonAuthenticated()
                .body(toJson(requestBody))
                .when()
                .put(endpoint);
    }

    public void sendDelete(String endpoint) {
        lastResponse = givenJsonAuthenticated()
                .when()
                .delete(endpoint);
    }

    public Response getLastResponse() {
        if (lastResponse == null) {
            throw new IllegalStateException("Nenhuma requisição foi executada ainda.");
        }
        return lastResponse;
    }

    public void sendGetWithoutAuth(String endpoint) {
        lastResponse = givenJsonWithoutAuth()
                .when()
                .get(endpoint);
    }

    public void sendGetWithCredentials(String endpoint, String username, String password) {
        lastResponse = givenJsonWithCredentials(username, password)
                .when()
                .get(endpoint);
    }

    private String toJson(Object requestBody) {
        if (requestBody == null) {
            return null;
        }
        if (requestBody instanceof String json) {
            return json;
        }
        return gson.toJson(requestBody);
    }
}
