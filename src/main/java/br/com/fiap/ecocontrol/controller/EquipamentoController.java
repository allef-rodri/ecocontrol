package br.com.fiap.ecocontrol.controller;

import br.com.fiap.ecocontrol.dto.EquipamentoCadastroDto;
import br.com.fiap.ecocontrol.dto.EquipamentoExibicaoDto;
import br.com.fiap.ecocontrol.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping("/listartodos")
    @ResponseStatus(HttpStatus.OK)
    public List<EquipamentoExibicaoDto> listar() {
        return equipamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipamentoExibicaoDto detalhar(@PathVariable Long id) {
        return equipamentoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<EquipamentoExibicaoDto> cadastrar(
            @RequestBody @Valid EquipamentoCadastroDto dto,
            UriComponentsBuilder uriBuilder
    ) {
        EquipamentoExibicaoDto equipamento = equipamentoService.criar(dto);
        URI uri = uriBuilder.path("/equipamentos/{id}")
                .buildAndExpand(equipamento.idEquipamento()).toUri();
        return ResponseEntity.created(uri).body(equipamento);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipamentoExibicaoDto atualizar(
            @PathVariable Long id,
            @RequestBody @Valid EquipamentoCadastroDto dto
    ) {
        return equipamentoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        equipamentoService.deletar(id);
    }
}