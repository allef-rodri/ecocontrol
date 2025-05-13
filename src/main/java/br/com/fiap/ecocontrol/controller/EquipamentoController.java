package br.com.fiap.ecocontrol.controller;

import br.com.fiap.ecocontrol.dto.EquipamentoCadastroDto;
import br.com.fiap.ecocontrol.dto.EquipamentoExibicaoDto;
import br.com.fiap.ecocontrol.exception.equipamento.*;
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
    public ResponseEntity<List<EquipamentoExibicaoDto>> listar() {
        try {
            return ResponseEntity.ok(equipamentoService.listarTodos());
        } catch (Exception e) {
            throw new ErroListagemEquipamentoException("Erro ao listar os equipamentos.");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EquipamentoExibicaoDto> detalhar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(equipamentoService.buscarPorId(id));
        } catch (Exception e) {
            throw new EquipamentoNaoEncontradoException("Equipamento com ID " + id + " não encontrado.");
        }
    }

    @PostMapping
    public ResponseEntity<EquipamentoExibicaoDto> cadastrar(
            @RequestBody @Valid EquipamentoCadastroDto dto,
            UriComponentsBuilder uriBuilder
    ) {
        try {
            EquipamentoExibicaoDto equipamento = equipamentoService.criar(dto);
            URI uri = uriBuilder.path("/equipamentos/{id}")
                    .buildAndExpand(equipamento.idEquipamento()).toUri();
            return ResponseEntity.created(uri).body(equipamento);
        } catch (Exception e) {
            throw new ErroCadastroEquipamentoException("Erro ao cadastrar o equipamento.");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EquipamentoExibicaoDto> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid EquipamentoCadastroDto dto
    ) {
        try {
            return ResponseEntity.ok(equipamentoService.atualizar(id, dto));
        } catch (Exception e) {
            throw new ErroAtualizacaoEquipamentoException("Erro ao atualizar o equipamento com ID " + id);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            equipamentoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ErroExclusaoEquipamentoException("Erro ao excluir o equipamento com ID " + id);
        }
    }
}