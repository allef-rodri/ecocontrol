package br.com.fiap.ecocontrol.controller;

import br.com.fiap.ecocontrol.dto.LeituraConsumoCadastroDto;
import br.com.fiap.ecocontrol.dto.LeituraConsumoExibicaoDto;
import br.com.fiap.ecocontrol.exception.leitura.ErroCadastroLeituraException;
import br.com.fiap.ecocontrol.exception.leitura.ErroListagemLeituraException;
import br.com.fiap.ecocontrol.service.LeituraConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leituras")
public class LeituraConsumoController {

    @Autowired
    private LeituraConsumoService service;

    @GetMapping
    public ResponseEntity<List<LeituraConsumoExibicaoDto>> listarTodasAsLeituras() {
        try {
            return ResponseEntity.ok(service.listar());
        } catch (Exception e) {
            throw new ErroListagemLeituraException("Erro ao listar leituras de consumo.");
        }
    }

    @PostMapping
    public ResponseEntity<LeituraConsumoExibicaoDto> cadastrar(@RequestBody LeituraConsumoCadastroDto dto) {
        try {
            LeituraConsumoExibicaoDto leituraSalva = service.cadastrar(dto);
            return ResponseEntity.status(201).body(leituraSalva);
        } catch (Exception e) {
            throw new ErroCadastroLeituraException("Erro ao cadastrar leitura de consumo.");
        }
    }
}
