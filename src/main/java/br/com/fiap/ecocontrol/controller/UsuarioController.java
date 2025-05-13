package br.com.fiap.ecocontrol.controller;

import br.com.fiap.ecocontrol.exception.usuario.UsuarioInvalidoException;
import br.com.fiap.ecocontrol.model.Usuario;
import br.com.fiap.ecocontrol.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody Usuario usuario) {
        try {
            if (repository.findByLogin(usuario.getLogin()).isPresent()) {
                throw new UsuarioInvalidoException("Usuário com este login já existe.");
            }

            usuario.setSenha(encoder.encode(usuario.getSenha()));
            repository.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (UsuarioInvalidoException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
