package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.AlertasResponseTestDto;

public class ListarTodosAlertasService {

    // Base URL do servidor
    private final String baseUrl = "http://localhost:8080";

    // Path do endpoint específico
    private final String listarAlertasPath = "/api/alertas/listartodos";

    private final Gson gson = new GsonBuilder().create();

    public Response response;

    /**
     * Executa a requisição GET simples para listar todos os alertas
     */
    public void listarTodosAlertas() {
        response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .get(baseUrl + listarAlertasPath);
    }

    /**
     * Desserializa a resposta para a model AlertasResponseTestDto
     */
    public AlertasResponseTestDto getAlertasResponse() {
        if (response == null) {
            throw new IllegalStateException("Nenhuma requisição executada ainda.");
        }
        return gson.fromJson(response.getBody().asString(), AlertasResponseTestDto.class);
    }

    /**
     * Retorna status code da última requisição
     */
    public int getStatusCode() {
        if (response == null) {
            throw new IllegalStateException("Nenhuma requisição executada ainda.");
        }
        return response.getStatusCode();
    }
}
