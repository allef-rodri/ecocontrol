package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import services.ApiRequestService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CommonApiSteps {

    private final ApiRequestService apiService = new ApiRequestService();
    private final Map<String, Object> contexto = new HashMap<>();
    private Response response;

    @Dado("que a API esteja autenticada")
    public void queApiEstejaAutenticada() {
        assertNotNull(apiService, "Serviço da API não foi inicializado");
    }

    @Dado("que a API esteja disponível")
    public void queApiEstejaDisponivel() {
        queApiEstejaAutenticada();
    }

    @Quando("eu enviar uma requisição {string} para o endpoint {string}")
    public void euEnviarUmaRequisicaoParaOEndpoint(String metodo, String endpoint) {
        executeRequest(metodo, endpoint, null);
    }

    @Quando("eu enviar uma requisição {string} para o endpoint {string} com o corpo:")
    public void euEnviarUmaRequisicaoParaOEndpointComOCorpo(String metodo, String endpoint, String corpo) {
        executeRequest(metodo, endpoint, corpo);
    }

    @Quando("eu enviar uma requisição {string} sem autenticação para o endpoint {string}")
    public void euEnviarUmaRequisicaoSemAutenticacaoParaOEndpoint(String metodo, String endpoint) {
        executeRequestWithoutAuth(metodo, endpoint, null);
    }

    @Quando("eu enviar uma requisição {string} sem autenticação para o endpoint {string} com o corpo:")
    public void euEnviarUmaRequisicaoSemAutenticacaoParaOEndpointComOCorpo(String metodo, String endpoint, String corpo) {
        executeRequestWithoutAuth(metodo, endpoint, corpo);
    }

    @Quando("eu enviar uma requisição {string} com credenciais {string} e {string} para o endpoint {string}")
    public void euEnviarUmaRequisicaoComCredenciaisParaOEndpoint(String metodo, String usuario, String senha, String endpoint) {
        executeRequestWithCredentials(metodo, endpoint, null, usuario, senha);
    }

    @Quando("eu enviar uma requisição {string} com credenciais {string} e {string} para o endpoint {string} com o corpo:")
    public void euEnviarUmaRequisicaoComCredenciaisParaOEndpointComOCorpo(String metodo, String usuario, String senha, String endpoint, String corpo) {
        executeRequestWithCredentials(metodo, endpoint, corpo, usuario, senha);
    }

    @Quando("eu enviar uma requisição {string} para o endpoint {string} utilizando o id armazenado em {string}")
    public void euEnviarUmaRequisicaoParaOEndpointUtilizandoOIdArmazenadoEm(String metodo, String endpointTemplate, String chave) {
        Object valor = contexto.get(chave);
        assertNotNull(valor, "Nenhum valor encontrado no contexto para a chave: " + chave);
        String endpoint = endpointTemplate.replace("{id}", String.valueOf(valor));
        executeRequest(metodo, endpoint, null);
    }

    @Quando("eu enviar uma requisição {string} para o endpoint {string} com o corpo utilizando o id armazenado em {string}:")
    public void euEnviarUmaRequisicaoParaOEndpointComOCorpoUtilizandoOIdArmazenadoEm(String metodo, String endpointTemplate, String chave, String corpo) {
        Object valor = contexto.get(chave);
        assertNotNull(valor, "Nenhum valor encontrado no contexto para a chave: " + chave);
        String endpoint = endpointTemplate.replace("{id}", String.valueOf(valor));
        executeRequest(metodo, endpoint, corpo);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCodeEsperado) {
        assertNotNull(response, "Nenhuma resposta foi obtida da API");
        assertEquals(statusCodeEsperado, response.getStatusCode(), "Status code não corresponde ao esperado");
    }

    @E("o corpo da resposta deve corresponder ao esquema JSON {string}")
    public void oCorpoDaRespostaDeveCorresponderAoEsquemaJSON(String schemaPath) {
        assertNotNull(response, "Nenhuma resposta foi obtida da API");
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }

    @E("a resposta deve conter o campo {string}")
    public void aRespostaDeveConterOCampo(String campo) {
        assertNotNull(response, "Nenhuma resposta foi obtida da API");
        Object valor = response.jsonPath().get(campo);
        assertNotNull(valor, "Campo " + campo + " não encontrado na resposta");
    }

    @E("a resposta deve conter {int} itens no campo {string}")
    public void aRespostaDeveConterItensNoCampo(int quantidade, String campoLista) {
        assertNotNull(response, "Nenhuma resposta foi obtida da API");
        var lista = response.jsonPath().getList(campoLista);
        assertNotNull(lista, "Campo " + campoLista + " não encontrado na resposta");
        assertEquals(quantidade, lista.size(), "Quantidade de itens inesperada para o campo " + campoLista);
    }

    @E("a resposta deve conter ao menos {int} itens no campo {string}")
    public void aRespostaDeveConterAoMenosItensNoCampo(int minimo, String campoLista) {
        assertNotNull(response, "Nenhuma resposta foi obtida da API");
        var lista = response.jsonPath().getList(campoLista);
        assertNotNull(lista, "Campo " + campoLista + " não encontrado na resposta");
        assertTrue(lista.size() >= minimo, "Quantidade de itens menor que o mínimo esperado para " + campoLista);
    }

    @E("a resposta deve conter ao menos {int} itens")
    public void aRespostaDeveConterAoMenosItens(int minimo) {
        assertNotNull(response, "Nenhuma resposta foi obtida da API");
        var lista = response.jsonPath().getList("$");
        assertNotNull(lista, "Resposta não é uma lista");
        assertTrue(lista.size() >= minimo, "Quantidade de itens menor que o mínimo esperado");
    }

    @E("armazeno o valor do campo {string} como {string}")
    public void armazenoOValorDoCampoComo(String campo, String chave) {
        assertNotNull(response, "Nenhuma resposta foi obtida da API");
        Object valor = response.jsonPath().get(campo);
        assertNotNull(valor, "Campo " + campo + " não encontrado para armazenamento");
        contexto.put(chave, valor);
    }

    @E("a resposta deve conter a mensagem {string}")
    public void aRespostaDeveConterAMensagem(String mensagem) {
        assertNotNull(response, "Nenhuma resposta foi obtida da API");
        assertTrue(response.asString().contains(mensagem), "Mensagem esperada não encontrada na resposta");
    }

    @E("o campo {string} deve possuir o valor {string}")
    public void oCampoDevePossuirOValor(String campo, String valorEsperado) {
        assertNotNull(response, "Nenhuma resposta foi obtida da API");
        Object valor = response.jsonPath().get(campo);
        assertNotNull(valor, "Campo " + campo + " não encontrado na resposta");
        assertEquals(valorEsperado, String.valueOf(valor), "Valor do campo " + campo + " difere do esperado");
    }

    private void executeRequest(String metodo, String endpoint, String corpo) {
        String metodoNormalizado = metodo.trim().toUpperCase();
        String corpoResolvido = corpo == null ? null : resolvePlaceholders(corpo);
        switch (metodoNormalizado) {
            case "GET" -> apiService.sendGet(endpoint);
            case "POST" -> apiService.sendPost(endpoint, corpoResolvido);
            case "PUT" -> apiService.sendPut(endpoint, corpoResolvido);
            case "DELETE" -> apiService.sendDelete(endpoint);
            default -> throw new IllegalArgumentException("Método HTTP não suportado: " + metodo);
        }
        response = apiService.getLastResponse();
    }

    private void executeRequestWithoutAuth(String metodo, String endpoint, String corpo) {
        String metodoNormalizado = metodo.trim().toUpperCase();
        String corpoResolvido = corpo == null ? null : resolvePlaceholders(corpo);
        switch (metodoNormalizado) {
            case "GET" -> apiService.sendGetWithoutAuth(endpoint);
            case "POST" -> apiService.sendPostWithoutAuth(endpoint, corpoResolvido);
            default -> throw new IllegalArgumentException("Método HTTP sem autenticação não suportado: " + metodo);
        }
        response = apiService.getLastResponse();
    }

    private void executeRequestWithCredentials(String metodo, String endpoint, String corpo, String usuario, String senha) {
        String metodoNormalizado = metodo.trim().toUpperCase();
        String corpoResolvido = corpo == null ? null : resolvePlaceholders(corpo);
        switch (metodoNormalizado) {
            case "GET" -> apiService.sendGetWithCredentials(endpoint, usuario, senha);
            case "POST" -> apiService.sendPostWithCredentials(endpoint, corpoResolvido, usuario, senha);
            default -> throw new IllegalArgumentException("Método HTTP com credenciais personalizadas não suportado: " + metodo);
        }
        response = apiService.getLastResponse();
    }

    @E("defino o valor {string} como {string}")
    public void definoOValorComo(String valor, String chave) {
        contexto.put(chave, valor);
    }

    @E("defino um identificador aleatório como {string}")
    public void definoUmIdentificadorAleatorioComo(String chave) {
        contexto.put(chave, UUID.randomUUID().toString());
    }

    private String resolvePlaceholders(String corpo) {
        String resultado = corpo.replace("{{uuid}}", UUID.randomUUID().toString())
                .replace("{{timestamp}}", String.valueOf(System.currentTimeMillis()));

        for (Map.Entry<String, Object> entry : contexto.entrySet()) {
            String marcador = "<" + entry.getKey() + ">";
            resultado = resultado.replace(marcador, String.valueOf(entry.getValue()));
        }
        return resultado;
    }
}
