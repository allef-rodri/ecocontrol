package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import services.ListarTodosAlertasService;
import model.AlertasResponseTestDto;
import model.AlertaTestDto;
import model.EquipamentoTestDto;
import model.SetorTestDto;


import static org.junit.jupiter.api.Assertions.*;

public class ListarTodosAlertasSteps {

    private final ListarTodosAlertasService listarTodosAlertasService = new ListarTodosAlertasService();
    private AlertasResponseTestDto alertasResponse;

    @Dado("que a API esteja disponível")
    public void queAAPIEstejaDisponível() {
        // Aqui podemos apenas garantir que o baseUrl está correto ou fazer um health check se desejar
        // Por enquanto, apenas um placeholder
        assertNotNull(listarTodosAlertasService);
    }

    @Quando("eu enviar uma requisição GET para o endpoint {string}")
    public void euEnviarUmaRequisiçãoGETParaOEndpoint(String endpoint) {
        // Ignora o parâmetro endpoint, pois nossa service já possui o path definido
        listarTodosAlertasService.listarTodosAlertas();
        alertasResponse = listarTodosAlertasService.getAlertasResponse();
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCodeEsperado) {
        int statusCode = listarTodosAlertasService.getStatusCode();
        assertEquals(statusCodeEsperado, statusCode, "Status code não corresponde ao esperado");
    }

    @E("a resposta deve conter uma lista de alertas")
    public void aRespostaDeveConterUmaListaDeAlertas() {
        assertNotNull(alertasResponse.getContent(), "A lista de alertas não pode ser nula");
        assertFalse(alertasResponse.getContent().isEmpty(), "A lista de alertas não pode estar vazia");
    }

    @E("cada alerta deve possuir os campos {string}, {string}, {string}, {string} e {string}")
    public void cadaAlertaDevePossuirOsCamposE(String idAlerta, String tipoAlerta, String mensagem, String dataHoraAlerta, String equipamento) {
        for (AlertaTestDto alerta : alertasResponse.getContent()) {
            assertNotNull(alerta.getIdAlerta(), "Campo idAlerta não pode ser nulo");
            assertNotNull(alerta.getTipoAlerta(), "Campo tipoAlerta não pode ser nulo");
            assertNotNull(alerta.getMensagem(), "Campo mensagem não pode ser nulo");
            assertNotNull(alerta.getDataHoraAlerta(), "Campo dataHoraAlerta não pode ser nulo");
            assertNotNull(alerta.getEquipamento(), "Campo equipamento não pode ser nulo");
        }
    }
}
