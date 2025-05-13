package br.com.fiap.ecocontrol.controller;

import br.com.fiap.ecocontrol.dto.SetorCadastroDto;
import br.com.fiap.ecocontrol.dto.SetorExibicaoDto;
import br.com.fiap.ecocontrol.exception.setor.ErroAtualizarSetorException;
import br.com.fiap.ecocontrol.exception.setor.ErroCriarSetorException;
import br.com.fiap.ecocontrol.exception.setor.ErroExcluirSetorException;
import br.com.fiap.ecocontrol.exception.setor.SetorNaoEncontradoException;
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
        try {
            return ResponseEntity.ok(setorService.buscarPorId(id));
        } catch (SetorNaoEncontradoException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar setor por ID.", e);
        }
    }

    @PostMapping
    public ResponseEntity<SetorExibicaoDto> cadastrar(
            @RequestBody @Valid SetorCadastroDto dto,
            UriComponentsBuilder uriBuilder
    ) {
        try {
            SetorExibicaoDto setor = setorService.criar(dto);
            URI uri = uriBuilder.path("/api/setores/{id}")
                    .buildAndExpand(setor.idSetor()).toUri();
            return ResponseEntity.created(uri).body(setor);
        } catch (Exception e) {
            throw new ErroCriarSetorException("Erro ao cadastrar setor.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetorExibicaoDto> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid SetorCadastroDto dto
    ) {
        try {
            return ResponseEntity.ok(setorService.atualizar(id, dto));
        } catch (SetorNaoEncontradoException e) {
            throw e;
        } catch (Exception e) {
            throw new ErroAtualizarSetorException("Erro ao atualizar setor.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            setorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (SetorNaoEncontradoException e) {
            throw e;
        } catch (Exception e) {
            throw new ErroExcluirSetorException("Erro ao excluir setor.");
        }
    }
}