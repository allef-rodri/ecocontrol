package br.com.fiap.ecocontrol.controller;

import br.com.fiap.ecocontrol.dto.AlertaExibicaoDto;
import br.com.fiap.ecocontrol.exception.alerta.ErroListagemAlertasException;
import br.com.fiap.ecocontrol.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping("/listartodos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<AlertaExibicaoDto>> listarTodosOsAlertas(Pageable paginacao) {
        try {
            Page<AlertaExibicaoDto> alertas = alertaService.listarTodosOsAlertas(paginacao);
            return ResponseEntity.ok(alertas);
        } catch (Exception e) {
            throw new ErroListagemAlertasException("Erro ao listar os alertas.");
        }
    }

}
