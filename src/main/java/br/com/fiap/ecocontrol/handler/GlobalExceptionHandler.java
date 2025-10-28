package br.com.fiap.ecocontrol.handler;

import br.com.fiap.ecocontrol.exception.alerta.ErroListagemAlertasException;
import br.com.fiap.ecocontrol.exception.equipamento.*;
import br.com.fiap.ecocontrol.exception.healthStatus.HealthStatusException;
import br.com.fiap.ecocontrol.exception.leitura.ErroCadastroLeituraException;
import br.com.fiap.ecocontrol.exception.leitura.ErroListagemLeituraException;
import br.com.fiap.ecocontrol.exception.setor.ErroAtualizarSetorException;
import br.com.fiap.ecocontrol.exception.setor.ErroCriarSetorException;
import br.com.fiap.ecocontrol.exception.setor.ErroExcluirSetorException;
import br.com.fiap.ecocontrol.exception.setor.SetorNaoEncontradoException;
import br.com.fiap.ecocontrol.exception.usuario.UsuarioInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SetorNaoEncontradoException.class)
    public ResponseEntity<String> handleNotFound(SetorNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({
            ErroCriarSetorException.class,
            ErroAtualizarSetorException.class,
            ErroExcluirSetorException.class
    })
    public ResponseEntity<String> handleInternal(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }


    @ExceptionHandler(ErroCadastroLeituraException.class)
    public ResponseEntity<String> handleCadastroLeitura(ErroCadastroLeituraException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(ErroListagemLeituraException.class)
    public ResponseEntity<String> handleListagemLeitura(ErroListagemLeituraException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(EquipamentoNaoEncontradoException.class)
    public ResponseEntity<String> handleNaoEncontrado(EquipamentoNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({
            ErroCadastroEquipamentoException.class,
            ErroAtualizacaoEquipamentoException.class,
            ErroExclusaoEquipamentoException.class,
            ErroListagemEquipamentoException.class
    })
    public ResponseEntity<String> handleErrosEquipamento(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(ErroListagemAlertasException.class)
    public ResponseEntity<String> handleErroListagemAlertas(ErroListagemAlertasException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(HealthStatusException.class)
    public ResponseEntity<String> handleHealthStatusException(HealthStatusException ex) {
        return ResponseEntity.status(500).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioInvalidoException.class)
    public ResponseEntity<String> handleUsuarioInvalido(UsuarioInvalidoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeral(Exception e) {
        return ResponseEntity.status(500).body("Erro inesperado: " + e.getMessage());
    }
}
