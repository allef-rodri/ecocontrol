package br.com.fiap.ecocontrol.controller;

import br.com.fiap.ecocontrol.dto.AlertaExibicaoDto;
import br.com.fiap.ecocontrol.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping("/listartodos")
    @ResponseStatus(HttpStatus.OK)
    public Page<AlertaExibicaoDto> listarTodosOsAlertas(Pageable paginacao) {
        return alertaService.listarTodosOsAlertas(paginacao);
    }

}
