package br.com.fiap.ecocontrol.controller;

import br.com.fiap.ecocontrol.dto.SetorCadastroDto;
import br.com.fiap.ecocontrol.dto.SetorExibicaoDto;
import br.com.fiap.ecocontrol.service.SetorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/setores")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @GetMapping
    public ResponseEntity<List<SetorExibicaoDto>> listar() {
        return ResponseEntity.ok(setorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetorExibicaoDto> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(setorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<SetorExibicaoDto> cadastrar(
            @RequestBody @Valid SetorCadastroDto dto,
            UriComponentsBuilder uriBuilder
    ) {
        SetorExibicaoDto setor = setorService.criar(dto);
        URI uri = uriBuilder.path("/api/setores/{id}").buildAndExpand(setor.idSetor()).toUri();
        return ResponseEntity.created(uri).body(setor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetorExibicaoDto> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid SetorCadastroDto dto
    ) {
        return ResponseEntity.ok(setorService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        setorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}