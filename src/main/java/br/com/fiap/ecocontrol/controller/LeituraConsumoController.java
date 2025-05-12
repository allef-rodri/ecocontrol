package br.com.fiap.ecocontrol.controller;

import br.com.fiap.ecocontrol.dto.LeituraConsumoExibicaoDto;
import br.com.fiap.ecocontrol.service.LeituraConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leituras")
public class LeituraConsumoController {

    @Autowired
    private LeituraConsumoService service;

    @GetMapping
    public List<LeituraConsumoExibicaoDto> listarTodasAsLeituras() {
        return service.listar();
    }
}
